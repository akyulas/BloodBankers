package com.example.jodiakyulas.bloodbankers

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.example.jodiakyulas.bloodbankers.control.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import com.zhuinden.espressohelper.*
import com.example.jodiakyulas.bloodbankers.R.id.*
import com.example.jodiakyulas.bloodbankers.control.AppointmentActivity
import java.util.*
import kotlin.concurrent.schedule

@RunWith(AndroidJUnit4::class)
public class MainActivityTest {

    @get:Rule
    public var rule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun checkIfAppointmentActivityLoads() {
        val activity = rule.activity
        main_page_view_appointment_button.performClick()
        Timer().schedule(10000){
            checkCurrentActivityIs<AppointmentActivity>()
        }

    }

}