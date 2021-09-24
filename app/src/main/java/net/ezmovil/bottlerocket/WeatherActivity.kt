package net.ezmovil.bottlerocket

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.bumptech.glide.Glide
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener
import com.google.android.material.appbar.CollapsingToolbarLayout
import net.ezmovil.bottlerocket.info.infoACKS
import net.ezmovil.bottlerocket.info.infoACKSDetail
import net.ezmovil.bottlerocket.info.infoContext
import net.ezmovil.bottlerocket.info.infoWeatherImage
import org.apache.http.HttpResponse
import org.apache.http.HttpStatus
import org.apache.http.client.ClientProtocolException
import org.apache.http.client.HttpClient
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.DefaultHttpClient
import org.json.JSONArray
import org.json.JSONObject
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.util.*

class WeatherActivity : AppCompatActivity() {

    var arrayHourlyWeather = ArrayList<JSONArray>()
    var arrayDays = ArrayList<JSONObject>()

    private var recyclerView: RecyclerView? = null
    private var adapter: AlbumAdapter3? = null
    private var albumList: List<Album3>? = null


    var arrayName = ArrayList<String>()
    var arrayImages = ArrayList<String>()
    var arrayEpisodes = ArrayList<Int>()
    var arrayBitmap = ArrayList<Bitmap>()
    var imageView: ImageView? = null

    var a: Album3? = null

    var b: Bitmap? = null

    var context: Context? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)
        val toolbar = findViewById<Toolbar>(R.id.toolbar_weather)

        val _infoWeatherImage = infoWeatherImage()

        val name: TextView = findViewById(R.id.love_music_weather) as TextView
        name.text = _infoWeatherImage.name

        setSupportActionBar(toolbar)

        context = this

        val _infoContext = infoContext()
        _infoContext.set_context(context)

        imageView = findViewById<ImageView>(R.id.thumbnail_weather)

        val parentLayout = findViewById<View>(android.R.id.content)
        initCollapsingToolbar()

        recyclerView = findViewById<View>(R.id.recycler_view) as RecyclerView

        albumList = ArrayList()
        adapter = AlbumAdapter3(this, albumList)

        val mLayoutManager: RecyclerView.LayoutManager = GridLayoutManager(this, 2)
        recyclerView!!.setLayoutManager(mLayoutManager)
        recyclerView!!.addItemDecoration(
            WeatherActivity.GridSpacingItemDecoration(
                2,
                dpToPx(10),
                true
            )
        )
        recyclerView!!.setItemAnimator(DefaultItemAnimator())
        recyclerView!!.setAdapter(adapter)

        try {

            Glide.with(this).load(_infoWeatherImage.txt)
                .into((findViewById<View>(R.id.backdrop_weather) as ImageView))

        } catch (e: Exception) {
            e.printStackTrace()
        }

        val task2: WeatherActivity.apiListCitiesDetail = WeatherActivity.apiListCitiesDetail()
        val t2 = Thread(task2, "Thread - 2")

        // now, let's start all three threads
        t2.start()

        try {
            t2.join()
            if (!t2.isAlive) {

                val _infoACKSDetail = infoACKSDetail()
                if (_infoACKSDetail.getToken() != null) {
                    prepareListCitiesDetail()

                    var myInt: Int = 0

                    for (i in arrayDays) {
                        a = Album3(
                            arrayDays.get(myInt)["low"].toString(),
                            arrayDays.get(myInt)["weatherType"].toString(),
                            arrayDays.get(myInt)["dayOfTheWeek"].toString(),
                            arrayDays.get(myInt)["high"].toString()
                        )
                        (albumList as ArrayList<Album3>).add(a!!)

                        myInt = myInt?.inc()
                    }

                    /*
                    for (i in 0..6) {
                        val Matrix = arrayOf(arrayDays, arrayHourlyWeather)
                        arrayDays.get(obj.getJSONObject(i))
                    }

                    val Matrix = arrayOf(arrayDays, arrayHourlyWeather)
                    val c = 0

                    */

                }
            }
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        adapter!!.notifyDataSetChanged()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }


    /**
     * Initializing collapsing toolbar
     * Will show and hide the toolbar title on scroll
     */
    private fun initCollapsingToolbar() {
        val collapsingToolbar = findViewById<View>(R.id.collapsing_toolbar_weather) as CollapsingToolbarLayout
        collapsingToolbar.title = " "
        val appBarLayout = findViewById<View>(R.id.appbar_weather) as AppBarLayout
        appBarLayout.setExpanded(true)

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(object : OnOffsetChangedListener {
            var isShow = false
            var scrollRange = -1
            override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.totalScrollRange
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.title = getString(R.string.app_name)
                    isShow = true
                } else if (isShow) {
                    collapsingToolbar.title = " "
                    isShow = false
                }
            }
        })
    }


    /**
     * Adding ListCities
     */
    private fun prepareListCitiesDetail() {
        val _infoACKSDetail = infoACKSDetail()
        val _token = _infoACKSDetail.token
        try {
            val obj = (JSONObject(_token).get("weather") as JSONObject).getJSONArray("days")

            for (i in 0..6) {
                arrayDays.add(obj.getJSONObject(i))
                /*for (j in 0..23) {
                    //arrayDays[i].get("hourlyWeather") as JSONArray
                    arrayHourlyWeather.add(arrayDays[i].get("hourlyWeather") as JSONArray)
                    val b=0
                }*/
            }
            //*****val Matrix = arrayOf(arrayDays, arrayHourlyWeather)

        } catch (ex: java.lang.Exception) {
            ex.printStackTrace()
        } catch (tx: Throwable) {
            Log.e("My App", "Could not parse malformed JSON: \"$_token\"")
        }
    }


    /**
    * RecyclerView item decoration - give equal margin around grid item
    */
    class GridSpacingItemDecoration(private val spanCount: Int, private val spacing: Int, private val includeEdge: Boolean) : ItemDecoration() {
        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            val position = parent.getChildAdapterPosition(view) // item position
            val column = position % spanCount // item column
            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount // (column + 1) * ((1f / spanCount) * spacing)
                if (position < spanCount) { // top edge
                    outRect.top = spacing
                }
                outRect.bottom = spacing // item bottom
            } else {
                outRect.left = column * spacing / spanCount // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing // item top
                }
            }
        }

    }

    /**
     * Converting dp to pixel
     */
    private fun dpToPx(dp: Int): Int {
        val r = resources
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), r.displayMetrics))
    }

    private class apiListCitiesDetail : Runnable {
        private var predecessor: Thread? = null
        override fun run() {
            println(Thread.currentThread().name + " Started 2")
            val httpclient: HttpClient = DefaultHttpClient()
            val response: HttpResponse
            var responseString: String? = null
            try {
                val _infoWeatherImage = infoWeatherImage()
                val geonameid = _infoWeatherImage.getGeonameid()
                val uritxt = "https://weather.exam.bottlerocketservices.com/cities/" + geonameid

                response = httpclient.execute(HttpGet(uritxt))

                val statusLine = response.statusLine
                if (statusLine.statusCode == HttpStatus.SC_OK) {
                    val out = ByteArrayOutputStream()
                    response.entity.writeTo(out)
                    responseString = out.toString()
                    out.close()
                } else {
                    //Closes the connection.
                    response.entity.content.close()
                    throw IOException(statusLine.reasonPhrase)
                }
            } catch (e: ClientProtocolException) {
                //TODO Handle problems..
                e.printStackTrace()
            } catch (e: IOException) {
                //TODO Handle problems..
                e.printStackTrace()
            }
            val _infoACKSDetail = infoACKSDetail()
            _infoACKSDetail.setAck(responseString)
            println(Thread.currentThread().name + " Finished")
        }

        fun setPredecessor(t: Thread?) {
            predecessor = t
        }
    }
}