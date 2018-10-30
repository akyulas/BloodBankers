package com.example.jodiakyulas.bloodbankers.control

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.jodiakyulas.bloodbankers.R

/**
 * This create a boundary class that shows the user the home page.
 */
class MainActivity : AppCompatActivity() {

    /**
     * Function that gets run on creation.
     * @param savedInstanceState The bundle saves the current instance of the activity.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
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

    }

    /**
     * Function to load the mobile blood drive page.
     */
    fun loadMobileBloodDrivePage(v: View) {
        val intent = Intent(this, MobileBloodDriveActivity::class.java)
        startActivity(intent)

    }
}
