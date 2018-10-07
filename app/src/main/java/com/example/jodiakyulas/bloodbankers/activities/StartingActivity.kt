package com.example.jodiakyulas.bloodbankers.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.example.jodiakyulas.bloodbankers.R

class StartingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing_page)

        val sharedPreference = getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE)
        var loggedIn = sharedPreference.getBoolean("hasLoggedIn", false)

        if (loggedIn) {
            val intent = Intent(this, MainActivity::class.java)
            Handler().postDelayed({
                startActivity(intent)}
            , 3000)
        } else {
            val intent = Intent(this, LoginActivity::class.java)
            Handler().postDelayed({
                startActivity(intent)}
                    , 3000)
        }

    }



}