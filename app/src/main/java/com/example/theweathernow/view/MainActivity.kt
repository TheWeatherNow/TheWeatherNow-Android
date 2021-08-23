package com.example.theweathernow.view

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.theweathernow.R
import com.example.theweathernow.databinding.ActivityMainBinding
import org.json.JSONObject
import java.lang.Exception
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    val CITY: String = "seoul"
    val API: String = "c06bcf4e4a98bf939f491630187005dc"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        weatherTask().execute()

    }

    inner class weatherTask() : AsyncTask<String, Void, String>() {
        override fun onPreExecute() {
            super.onPreExecute()
            mBinding.loader.visibility = View.VISIBLE
            mBinding.mainContainer.visibility = View.GONE
            mBinding.errertext.visibility = View.GONE

        }

        override fun doInBackground(vararg p0: String?): String? {
            var response:String?
            try {
                response = URL("https://api.openweathermap.org/data/2.5/weather?q=$CITY&units=metric&appid=$API")
                    .readText(Charsets.UTF_8)
            }
            catch (e: Exception) {
                response = null
            }
            return response
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            try {
                val jsonObj = JSONObject(result)
                val main = jsonObj.getJSONObject("main")
                val sys = jsonObj.getJSONObject("sys")
                val weather = jsonObj.getJSONArray("weather").getJSONObject(0)
                val updateAt:Long = jsonObj.getLong("dt")
                val updatedAtText = "Updated at: "+ SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.KOREA).format(
                    Date(updateAt*1000)
                )
                val temp = main.getString("temp")+"ºC"
                val weatherDescription = weather.getString("description")
                val address = jsonObj.getString("name")+", "+sys.getString("country")

                mBinding.mainLocal.text = address
                mBinding.updatedAt.text = updatedAtText
                mBinding.mainAnnouncement.text = weatherDescription.capitalize()
                mBinding.mainTemp.text = temp

                mBinding.loader.visibility = View.GONE
                mBinding.mainContainer.visibility = View.VISIBLE

//                when (mBinding.mainAnnouncement.toString()) {
//                    "Rain" -> mBinding.mainWeatherImg.setAnimation()
//                    "Clear" -> mBinding.mainWeatherImg.setAnimation()
//                    "Clouds" -> mBinding.mainWeatherImg.setAnimation()
//                }


            }
            catch (e: Exception) {
                mBinding.loader.visibility = View.GONE
                mBinding.errertext.visibility = View.VISIBLE
            }
        }
    }
}