package com.example.jodiakyulas.bloodbankers.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.example.jodiakyulas.bloodbankers.R
import com.example.jodiakyulas.bloodbankers.adapters.BloodBankAdapter
import com.example.jodiakyulas.bloodbankers.classes.BloodBank
import com.example.jodiakyulas.bloodbankers.constants.Constants
import kotlinx.android.synthetic.main.activity_make_appointment.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay
import okhttp3.*
import java.io.IOException
import java.lang.StringBuilder

class AppointmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_make_appointment)
        val bloodBankRecyclerView = findViewById<RecyclerView>(R.id.bloodBankRecyclerView)
        val relativeLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        bloodBankRecyclerView.layoutManager = relativeLayoutManager
        bloodBankRecyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        val bloodBankAdapter = BloodBankAdapter(ArrayList<BloodBank>(), this)
        bloodBankRecyclerView.adapter = bloodBankAdapter
        populateAPIBloodBankAdapter(bloodBankAdapter)

    }

    fun populateAPIBloodBankAdapter(bloodBankAdapter: BloodBankAdapter) {
        val client = OkHttpClient()
        val getBloodBankURL = "http://10.0.2.2:8090/queryAPI"
        val request = Request.Builder().url(getBloodBankURL).build()
        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call, response: Response) {
                val bloodBanksJsonString = response.body()?.string()
                val bloodBanks = createBloodBankDataset(bloodBanksJsonString)
                runOnUiThread {
                    bloodBankAdapter.setBloodBanks(bloodBanks)
                }

            }

            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

        })

    }

    fun createBloodBankDataset(bloodBanksJsonString: String?): ArrayList<BloodBank> {
        var bloodBanks = ArrayList<BloodBank>()
        val parser: Parser = Parser()
        var json: JsonObject = parser.parse(StringBuilder(bloodBanksJsonString)) as JsonObject
        var tempString = json.get("result") as JsonObject
        var tempJson = parser.parse(StringBuilder(tempString.toJsonString())) as JsonObject
        var tempJsonArray  = tempJson.get("records") as JsonArray<JsonObject>
        for ((index, obj) in tempJsonArray.withIndex()) {
            bloodBanks.add(createBloodBank(obj))
        }
        return bloodBanks
    }

    private fun createBloodBank(jsonObject: JsonObject) : BloodBank {
        val _id = jsonObject.get("_id").toString().toInt()
        val location = jsonObject.get("location").toString()
        val address = jsonObject.get("address").toString()
        val postalCode = jsonObject.get("postal_code").toString()
        val donationType = jsonObject.get("donation_type").toString()
        val mondayOperatingHour = jsonObject.get("monday_operating_hour").toString()
        val tuesdayOperatingHour = jsonObject.get("tuesday_operating_hour").toString()
        val wednesdayOperatingHour = jsonObject.get("wednesday_operating_hour").toString()
        val thursdayOperatingHour = jsonObject.get("thursday_operating_hour").toString()
        val fridayOperatingHour = jsonObject.get("friday_operating_hour").toString()
        val saturdayOperatingHour = jsonObject.get("saturday_operating_hour").toString()
        val sundayOperatingHour = jsonObject.get("sunday_operating_hour").toString()
        val eveOfMajorPublicHolidayOperatingHour = jsonObject.get("eve_of_major_public_holiday_operating_hour").toString()
        val publicHolidayOperatingHour = jsonObject.get("public_holiday_operating_hour").toString()
        return BloodBank(_id = _id, location = location, address = address, postalCode = postalCode, donationType = donationType,
                mondayOperatingHour = mondayOperatingHour, tuesdayOperatingHour =  tuesdayOperatingHour, wednesdayOperatingHour = wednesdayOperatingHour,
                thursdayOperatingHour = thursdayOperatingHour, fridayOperatingHour = fridayOperatingHour, saturdayOperatingHour = saturdayOperatingHour,
                sundayOperatingHour = sundayOperatingHour, eveOfMajorPublicHolidayOperatingHour = eveOfMajorPublicHolidayOperatingHour, publicHolidayOperatingHour = publicHolidayOperatingHour)
    }


}