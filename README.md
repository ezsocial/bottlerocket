# bottlerocket
Botle Rocket

# WeatherApp
Dowloand to install
Use https://github.com/ezsocial/bottlerocket/blob/main/bottlerocket.apk

Android Weather app developed in Kotlin. The app is composed of a single screen and the app widget.

Display the header (including city name, date, time and temperature for the
day), weekly (non-scrollable) and hourly forecast data (scrollable) in a list
view for a city of your choice (as part of the city list returned by the API
mentioned above.

• This demo gets and show the picture using recycle view
• Use https://allweather.docs.apiary.io/#reference/0/cities-collection/list-cities  All Weather API and consume using http standar with Threads simple
• Any user can select the image of the city and goes to the cloud icon to get inf of 7 days

<img align="right" src="https://github.com/ezsocial/bottlerocket/blob/main/Screenshot_20210924_000145.png" width="33%"/>

## Widgetelebuenos 
Displays up to date header image and cities with info from API listed below. The app has support for Android 10 and 11 by adding logic. 


## Libraries and tools used:
+ https to make REST requests to the web service integrated with Coroutine to run operations in the background without creating multiple callbacks. <br/>
+ RecyclerView and CardView to vie infor and the eserialization of the returned JSON to Kotlin data objects.<br/>
+ Android Architecture Components (ViewModel, LiveData, Data Binding, WorkManager).<br/>
+ The app accesses the set of supported location services through classes in the com.google.android.gms.location package.
