package com.example.jodiakyulas.bloodbankers.control

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.jodiakyulas.bloodbankers.R
import com.google.firebase.messaging.FirebaseMessaging

/**
 * This create a boundary class that shows the user the home page.
 */
class MainActivity : AppCompatActivity() {

    /**
     * Function that gets run on creation.
     * @param savedInstanceState The bundle saves the current instance of the activity.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        FirebaseMessaging.getInstance().subscribeToTopic("bloodbank")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    /**
     * Function to load the book the appointment page.
     */
    fun loadAppointmentPage(v: View) {
        val intent = Intent(this, AppointmentActivity::class.java)
        startActivity(intent)
    }

    /**
     * Function to load the current appointment page.
     */
    fun loadViewCurrentAppointmentPage(v: View) {
        val intent = Intent(this, ViewCurrentAppointmentActivity::class.java)
        startActivity(intent)
    }

    /**
     * Function to load the appointment history page.
    */
    fun loadViewAppointmentHistoryPage(v: View) {
        val intent = Intent(this, ViewAppointmentHistoryActivity::class.java)
        startActivity(intent)
    }

    /**
     * Function to load the faq page.
     */
    fun loadFAQPage(v: View) {
        val intent = Intent(this, FAQActivity::class.java)
        startActivity(intent)
    }

    fun loadRedemptionPage(v: View) {
        val intent = Intent(this, PointsRedemptionActivity::class.java)
        startActivity(intent)
    }

    /**
     * Function to load the mobile blood drive page.
     */
    fun loadMobileBloodDrivePage(v: View) {
        val intent = Intent(this, MobileBloodDriveActivity::class.java)
        startActivity(intent)

    }

    fun loadChangePasswordPage(v: View) {
        val intent = Intent(this, ChangePasswordActivity::class.java)
        startActivity(intent)
    }



    fun logout(v: View) {
        val sharedPreferences = getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("hasLoggedIn", true)
        editor.apply()
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}
