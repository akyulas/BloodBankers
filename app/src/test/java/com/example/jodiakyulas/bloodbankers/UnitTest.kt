package com.example.jodiakyulas.bloodbankers

import android.app.Instrumentation
import android.widget.Button
import com.example.jodiakyulas.bloodbankers.R.id.main_page_make_appointment_button
import com.example.jodiakyulas.bloodbankers.classes.BloodBank
import org.junit.Test
import com.example.jodiakyulas.bloodbankers.constants.Constants.backendURL;
import okhttp3.*
import org.junit.Assert.*
import java.io.IOException
import com.example.jodiakyulas.bloodbankers.adapters.BloodBankAdapter
import com.example.jodiakyulas.bloodbankers.activities.AppointmentActivity
import com.example.jodiakyulas.bloodbankers.activities.MainActivity
import kotlinx.android.synthetic.main.activity_main.*


class UnitTest {
    @Test
    fun canAccessBackendAPI() {
        val client = OkHttpClient()
        val getBloodBankURL = backendURL + "queryAPI"
        val request = Request.Builder().url(getBloodBankURL).build()
        val call = client.newCall(request);
        val response = call.execute()
        assertEquals(200, response.code())
    }

//    @Test
//    fun canCreateBloodBankDataset() {
//        assert(AppointmentActivity().createBloodBankDataset().get(0) is BloodBank)
//    }
//
//    @Test
//    fun checkIfBloodBankClassCreatedProperly() {
//        assertEquals("Bloodbank@HSA", AppointmentActivity().createBloodBankDataset().get(0).location)
//    }







}