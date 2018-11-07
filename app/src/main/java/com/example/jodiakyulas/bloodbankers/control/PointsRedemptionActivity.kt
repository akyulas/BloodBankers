package com.example.jodiakyulas.bloodbankers.control

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.TestLooperManager
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.TextView
import com.example.jodiakyulas.bloodbankers.R
import com.example.jodiakyulas.bloodbankers.entity.OkHttpSingleton
import com.example.jodiakyulas.bloodbankers.entity.ParticipatingMerchants
import okhttp3.*
import org.w3c.dom.Text
import java.io.IOException
import java.util.concurrent.TimeUnit

class PointsRedemptionActivity:AppCompatActivity() {

    private val merchantA = ParticipatingMerchants("McDonald", "Get $5 off any meal.", 2)
    private val merchantB = ParticipatingMerchants("KFC", "Get $3 off any meal.", 1)
    private val merchantC = ParticipatingMerchants("Koufu", "Get $3 off any meal,", 1)
    private val merchantD = ParticipatingMerchants("Prime supermarket", "Get any $3 off any items more than $5", 1)
    private val merchantE = ParticipatingMerchants("Starbucks", "Get $3 off any drinks", 1)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_points_redemption)
        getPoints()
        var participatingMerchants = ArrayList<ParticipatingMerchants>()
        participatingMerchants.add(merchantA)
        participatingMerchants.add(merchantB)
        participatingMerchants.add(merchantC)
        participatingMerchants.add(merchantD)
        participatingMerchants.add(merchantE)
        val participatingMerchantsRecyclerView = findViewById<RecyclerView>(R.id.pointsRedemptionMerchants)
        val relativeLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        participatingMerchantsRecyclerView.layoutManager = relativeLayoutManager
        participatingMerchantsRecyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        val appointmentAdapter = MerchantAdapter(participatingMerchants, this)
        participatingMerchantsRecyclerView.adapter = appointmentAdapter
    }

    fun getPoints() {
        val sharedPreferences = getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE)
        val matricID = sharedPreferences.getString("matricID", "hacker")

        val client = OkHttpSingleton.getClient()

        val pointsView = findViewById<TextView>(R.id.pointsTotalPoints)
        val pointsURL = "http://10.0.2.2:8090/getPoints?m=$matricID"

        val request = Request.Builder().url(pointsURL).build()

        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call, response: Response) {

                val returnValue = response.body()?.string()?.toString()?.toInt()

                runOnUiThread {

                    if (returnValue != -1) {
                        pointsView.text = returnValue.toString()
                        val sharedPreferences = getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE)
                        val editor = sharedPreferences.edit()
                        editor.putInt("points", returnValue!!)
                        editor.apply()
                    }

                }
            }

            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

        })

    }





}