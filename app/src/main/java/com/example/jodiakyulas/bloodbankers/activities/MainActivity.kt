package com.example.jodiakyulas.bloodbankers.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.jodiakyulas.bloodbankers.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    fun loadAppointmentPage(v: View) {
        val intent = Intent(this, AppointmentActivity::class.java)
        startActivity(intent)
    }
}
