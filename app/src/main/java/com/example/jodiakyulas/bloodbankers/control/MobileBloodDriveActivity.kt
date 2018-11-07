package com.example.jodiakyulas.bloodbankers.control

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.TextView
import com.beust.klaxon.Json
import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.example.jodiakyulas.bloodbankers.R
import com.example.jodiakyulas.bloodbankers.entity.BloodBank
import com.example.jodiakyulas.bloodbankers.entity.MobileBloodBank
import com.example.jodiakyulas.bloodbankers.entity.OkHttpSingleton
import okhttp3.*
import java.io.IOException
import java.lang.StringBuilder

/**
 * This create a boundary class that lets the user view the mobile blood drives.
 */
class MobileBloodDriveActivity: AppCompatActivity() {


    /**
     * Function that gets run on creation.
     * @param savedInstanceState The bundle saves the current instance of the activity.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mobile_blood_drive)
        var mobileBloodBanks = ArrayList<MobileBloodBank>();
        val mobileBloodBankRecyclerView = findViewById<RecyclerView>(R.id.mobile_blood_drive_recycler_view)
        val relativeLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mobileBloodBankRecyclerView.layoutManager = relativeLayoutManager
        mobileBloodBankRecyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        val mobileBloodBankAdapter = MobileBloodBankAdapter(mobileBloodBanks, this)
        mobileBloodBankRecyclerView.adapter = mobileBloodBankAdapter
        populateActivity(mobileBloodBankAdapter)
    }

    /**
     * Create a list of blood banks.
     * @param bloodBanksJsonString: The Json String that has been queried.
     * @return bloodBanks: The bloodbanks that has been created.
     */
    fun createMobileBloodBankDataset(mobileBloodBanksJsonString: String?): ArrayList<MobileBloodBank> {
        var mobileBloodBanks = ArrayList<MobileBloodBank>()
        val parser: Parser = Parser()
        var json = parser.parse(StringBuilder(mobileBloodBanksJsonString)) as JsonArray<JsonObject>
        for ((index, obj) in json.withIndex()) {
            mobileBloodBanks.add(createMobileBloodBank(obj))
        }
        return mobileBloodBanks
    }


    /**
     * Create a single mobile blood bank.
     * @param jsonObject: The Json Object that will be used to create blood banks.
     * @return BloodBank: The blood bank object that has been created.
     */
    fun createMobileBloodBank(jsonObject: JsonObject) : MobileBloodBank {
        val location = jsonObject.get("location").toString()
        var timings = createDateAndTime(jsonObject.get("driveTimings") as JsonArray<JsonObject>)
        val address = jsonObject.get("address").toString()
        return MobileBloodBank(location = location, datesAndTimes = timings, address = address)
    }

    fun createDateAndTime(jsonArray: JsonArray<JsonObject>) : ArrayList<MobileBloodBank.MobileDriveDateAndTime> {
        val mobileDriveTimings = ArrayList<MobileBloodBank.MobileDriveDateAndTime>()
        for ((index, obj) in jsonArray.withIndex()) {
            val date = obj.get("driveDate").toString()
            val timing = obj.get("timings").toString()
            mobileDriveTimings.add(MobileBloodBank.MobileDriveDateAndTime(date, timing))
        }
        return mobileDriveTimings
    }


    fun populateActivity(mobileBloodBankAdapter: MobileBloodBankAdapter) {
        val client = OkHttpSingleton.getClient()

        val mobileBloodBank = "http://10.0.2.2:8090/mobileBloodBank"
        val request = Request.Builder().url(mobileBloodBank).build()

        client.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {

                val bloodBanksJsonString = response.body()?.string()

                runOnUiThread {

                    if (bloodBanksJsonString.equals("")) {


                    } else {

                        val bloodBanks = createMobileBloodBankDataset(bloodBanksJsonString)
                        mobileBloodBankAdapter.setBloodBanks(bloodBanks)

                    }

                }

            }
        })

    }
}