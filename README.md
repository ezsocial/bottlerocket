# bottlerocket
Botle Rocket

# WeatherApp
<img align="right" src="previews/preview_1.gif" width="33%"/>
Android Weather app developed in Kotlin. The app is composed of a single screen and the app widget.

Display the header (including city name, date, time and temperature for the
day), weekly (non-scrollable) and hourly forecast data (scrollable) in a list
view for a city of your choice (as part of the city list returned by the API
mentioned above.

• This demo gets and show the picture using recycle view
• Use https://allweather.docs.apiary.io/#reference/0/cities-collection/list-cities  All Weather API and consume using http standar with Threads simple
• Any user can select the image of the city and goes to the cloud icon to get inf of 7 days

<img align="right" src="https://github.com/ezsocial/bottlerocket/blob/main/Screenshot_20210924_000145.png" width="33%"/>

![Screenshot](https://github.com/ezsocial/bottlerocket/blob/main/Screenshot_20210924_000145.png)

## Widget
Displays up to date header image and cities with info from API listed below. The app has support for Android 10 and 11 by adding logic. 


## Libraries and tools used:
+ https to make REST requests to the web service integrated with Coroutine to run operations in the background without creating multiple callbacks. <br/>
+ RecyclerView and CardView to vie infor and the eserialization of the returned JSON to Kotlin data objects.<br/>
+ Android Architecture Components (ViewModel, LiveData, Data Binding, WorkManager).<br/>
+ The app accesses the set of supported location services through classes in the com.google.android.gms.location package.

## License

[![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg?style=flat-square)](https://www.apache.org/licenses/LICENSE-2.0.html)

```
Copyright 2020 Ersiver

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

