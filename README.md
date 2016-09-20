DNALoading  
===
[![API](https://img.shields.io/badge/API-11%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=11) 

The DNALoading imitates [dna-loading](https://www.uplabs.com/posts/dna-loading)

ScreenShot
--
![](https://github.com/DennyCai/DNALoading/blob/master/screenshots/1471640905.gif)

Download
--
#### Gradle
```groovy
compile 'com.denny.dnaloading:library:0.5.0'
```

Usage
--

#### Default Style
```xml
    <com.denny.dnaloading.widget.DnaLoadingView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"/>
```

#### Custom Style
```xml
    <com.denny.dnaloading.widget.DnaLoadingView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:dna_amp="50dp"
        app:dna_phase="1.0"
        app:dna_duration="2000"
        app:dna_radius="5dp"
        app:dna_dashWith="2dp"
        app:dna_colorPrimary="#ffee00"
        app:dna_colorAccent="#00ffaa"/>
```
#### Attributes:
| attribute          | description |
|:---				 |:---|
| dna_amp  	     | distance of from center to top or bottom |
| dna_phase     | cycle |
| dna_duration 	     | animation duration |
| dna_radius 	     | dot radius |
| dna_dashWith 	     | the distance between the dots |
| dna_colorPrimary 	     | color 1 |
| dna_colorAccent  	     | color 2 |

Author
--
* Email: dennycai2015@gmail.com

License
--
```
Copyright 2015-2019 DennyCai

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
