package com.example.jodiakyulas.bloodbankers.control

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import com.example.jodiakyulas.bloodbankers.R
import com.example.jodiakyulas.bloodbankers.entity.ParticipatingMerchants
import okhttp3.*
import java.io.IOException
import java.util.concurrent.TimeUnit

class RedemptionDetailActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_redemption_details)
        populateField()
    }

    fun populateField() {
        val merchantBundle = intent.extras

        val merchantDetails = merchantBundle.getSerializable("ParticipatingMerchants") as ParticipatingMerchants

        val merchantNameView = findViewById<TextView>(R.id.merchantName)
        merchantNameView.append(merchantDetails.merchantName)

        val merchantTextView = findViewById<TextView>(R.id.merchantText)
        merchantTextView.append(merchantDetails.merchantText)

        val merchantPointsView = findViewById<TextView>(R.id.merchantPoints)
        merchantPointsView.append(merchantDetails.merchantPoints.toString())

    }

    fun confirmVouncherPayment(v: View) {
        val client = OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS).build()

        val sharedPreferences = getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE)
        val matricID = sharedPreferences.getString("matricID", "hacker")

        val merchantBundle = intent.extras

        val merchantDetails = merchantBundle.getSerializable("ParticipatingMerchants") as ParticipatingMerchants

        val merchantName = merchantDetails.merchantName
        val merchantText = merchantDetails.merchantText
        val merchantPoints = merchantDetails.merchantPoints.toString()

        val passcodeURL = "http://10.0.2.2:8090/deductPoints/$matricID/$merchantPoints/$merchantName/$merchantText"

        val request = Request.Builder().url(passcodeURL).build()

        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call, response: Response) {

                val returnString = response.body()?.string()

                runOnUiThread {

                    if (returnString.equals("SUCCESS")) {

                        val builder = AlertDialog.Builder(this@RedemptionDetailActivity)
                        builder.setPositiveButton(android.R.string.ok) {
                            dialog, which ->
                            val intent = Intent(this@RedemptionDetailActivity, MainActivity::class.java)
                            startActivity(intent)
                        }
                        builder.setTitle("Success")
                        builder.setMessage("Please check your email.")
                        val dialog: AlertDialog = builder.create()

                        dialog.show()


                    } else {

                        val builder = AlertDialog.Builder(this@RedemptionDetailActivity)
                        builder.setTitle("Something went wrong.")
                        builder.setMessage("Please try again later.")
                        val dialog: AlertDialog = builder.create()
                        dialog.show()

                    }

                }

            }

            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

        })
    }

}