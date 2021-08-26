package com.example.theweathernow.view.activity.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.theweathernow.R
import com.example.theweathernow.base.BaseActivity
import com.example.theweathernow.databinding.ActivityMainBinding
import java.lang.Exception
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : BaseActivity() {
    private val binding by binding<ActivityMainBinding>(R.layout.activity_main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setText()
    }

    private fun setText(){
        binding.mainLocal.text = intent.getStringExtra("name")
        binding.mainTemp.text = intent.getStringExtra("temp")
        binding.mainAnnouncement.text = intent.getStringExtra("weatherMain")
        binding.updatedAt.text = convertTimestampToDate(intent.getStringExtra("dt").toString().toLong())

    }

    fun convertTimestampToDate(timestamp: Long) : String{
        val sdf = SimpleDateFormat("yyyy년MM월dd일 hh시mm분")
        return sdf.format((System.currentTimeMillis())).toString()
    }

    fun toTimeStamp(num: Long): String {
        Log.d("로그","num : ${num}")

        val toTimeStamp = Date(num)
        Log.d("로그","$toTimeStamp")
        val dateF = SimpleDateFormat("yyyy-MM-dd hh:mm", Locale.getDefault())
        Log.d("로그","$dateF")
        return dateF.format(toTimeStamp)
    }
}