package com.example.jodiakyulas.bloodbankers

import android.os.Handler
import android.os.Looper
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.runner.AndroidJUnit4
import android.view.View
import android.widget.Button
import com.example.jodiakyulas.bloodbankers.activities.AppointmentActivity
import com.example.jodiakyulas.bloodbankers.activities.MainActivity

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

@RunWith(AndroidJUnit4::class)
class InstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.example.jodiakyulas.bloodbankers", appContext.packageName)
    }

}
