package com.xtha.zujal.kotlin_exercise

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.xtha.zujal.kotlin_exercise.Model.Product
import android.content.Intent



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //starting homescreen activity
        val intent = Intent(applicationContext, homescreen::class.java)
        startActivity(intent)

    }
}
