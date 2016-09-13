package com.denny.dnaloading.widget;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;

import com.denny.dnaloading.R;


public class DnaLoadingView extends View {
    private int mWidth, mHeight;
    private int mRadius = 10;
    private int mDashWith = 10;
    private Paint mCirclePaint;
    private float mOffset = 0;
    private float mFactor;
    private int mAmp;
    private int mPointCount;
    private Paint mCirclePaint2;
    private ValueAnimator mAnim;
    private long mDuration = 5000;
    private float mScale;
    private int mPrimaryColor,mAccentColor;

    public DnaLoadingView(Context context) {
        this(context, null);
    }

    public DnaLoadingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DnaLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs,defStyleAttr);
    }

    private void init(AttributeSet attrs,int defaStyleAttr) {
        TypedArray array = getContext().obtainStyledAttributes(attrs,R.styleable.DnaLoadingView,defaStyleAttr,R.style.DnaLoadingView);

        mAmp = (int) array.getDimension(R.styleable.DnaLoadingView_dna_amp,getResources().getDimension(R.dimen.default_amp));
        mScale = array.getFloat(R.styleable.DnaLoadingView_dna_phase,1.0f);
        mPrimaryColor = array.getColor(R.styleable.DnaLoadingView_dna_colorPrimary,Color.GRAY);
        mAccentColor = array.getColor(R.styleable.DnaLoadingView_dna_colorAccent,Color.DKGRAY);
        mRadius = (int) array.getDimension(R.styleable.DnaLoadingView_dna_radius,10);
        mDashWith = (int) array.getDimension(R.styleable.DnaLoadingView_dna_dashWith,10);
        mDuration = array.getInteger(R.styleable.DnaLoadingView_dna_duration,6000);

        array.recycle();

        mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCirclePaint.setColor(mPrimaryColor);
        mCirclePaint2 = new Paint(mCirclePaint);
        mCirclePaint2.setColor(mAccentColor);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heigthMode = MeasureSpec.getMode(heightMeasureSpec);
        switch (widthMode) {
            case MeasureSpec.AT_MOST:
                mWidth = (int) Math.min(MeasureSpec.getSize(widthMeasureSpec), getResources().getDimension(R.dimen.dna_loding_view_default_width));
                break;
            case MeasureSpec.UNSPECIFIED:
                mWidth = (int) getResources().getDimension(R.dimen.dna_loding_view_default_width);
                break;
            case MeasureSpec.EXACTLY:
                mWidth = MeasureSpec.getSize(widthMeasureSpec);
                break;
        }
        switch (heigthMode) {
            case MeasureSpec.AT_MOST:
                mHeight = getHeightByAmp();
                break;
            case MeasureSpec.UNSPECIFIED:
                mHeight = (int) getResources().getDimension(R.dimen.dna_loding_view_default_width);
                break;
            case MeasureSpec.EXACTLY:
                mHeight = MeasureSpec.getSize(heightMeasureSpec);
                break;
        }
        setMeasuredDimension(mWidth,mHeight);
        calcPoints();
    }

    private int getHeightByAmp() {
        return mAmp*2+2*mRadius;
    }

    private void calcPoints() {
        int width = mWidth - 2*mRadius;
        mPointCount = width/(2*mRadius+ mDashWith);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        canvas.translate(mRadius,mHeight>>1);
        for(int i =0;i<mPointCount;++i) {
            canvas.drawCircle(getDx(i), getDy(i*1.0f / mPointCount), mRadius, mCirclePaint);
            canvas.drawCircle(getDx(i)+2*mRadius,-getDy(i*1.0f/mPointCount),mRadius,mCirclePaint2);
        }
        canvas.restore();
    }

    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
        if(visibility==GONE)
            stopAnim();
        if(visibility==VISIBLE)
            startAnim();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        startAnim();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopAnim();
    }

    private void startAnim() {
        Animator animator = getAnim();
        if(!animator.isRunning())
            animator.start();
    }


    private void stopAnim() {
        Animator animator = getAnim();
        if(animator.isRunning())
            animator.cancel();
    }

    private float getDx(int i) {
        return (2*mRadius+ mDashWith)*i;
    }

    private float getDy(float offset) {
        return (float) (mAmp*Math.sin(2*Math.PI*mFactor+ offset*2*Math.PI*mScale));
    }


    public Animator getAnim() {
        if(mAnim!=null)
            return mAnim;
        mAnim = ValueAnimator.ofFloat(0,1f).setDuration(mDuration);
        mAnim.setInterpolator(new LinearInterpolator());
        mAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mFactor = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        mAnim.setRepeatCount(Animation.INFINITE);
        mAnim.setRepeatMode(Animation.RESTART);
        return mAnim;
    }
}
