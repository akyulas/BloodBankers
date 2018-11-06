package com.example.jodiakyulas.bloodbankers.control

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import com.example.jodiakyulas.bloodbankers.R
import okhttp3.*
import java.io.IOException
import java.util.concurrent.TimeUnit

class EnterPasscodeActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.android_enter_passcode)
    }

    fun verifyPassCode(v: View) {
        val client = OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS).build()
        val passcode = findViewById<TextView>(R.id.forget_enter_passcode_text_box).text.toString()

        val sharedPreferences = getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE)
        val email = sharedPreferences.getString("email", "wrong-email")

        val passcodeURL = "http://10.0.2.2:8090/passcode/$passcode/$email"

        val request = Request.Builder().url(passcodeURL).build()

        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call, response: Response) {

                val returnString = response.body()?.string()

                runOnUiThread {

                    if (returnString.equals("Succeeded")) {

                        val intent = Intent(this@EnterPasscodeActivity, ChangePasswordActivity::class.java)
                        startActivity(intent)


                    } else {

                        val builder = AlertDialog.Builder(this@EnterPasscodeActivity)
                        builder.setTitle("Something went wrong.")
                        builder.setMessage("Please ensure you enter the pass code correctly or try again later.")
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

    fun sendEmail(v: View) {
        val client = OkHttpClient()
        val sharedPreferences = getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE)

        val email = sharedPreferences.getString("email", "wrong-email")

        val emailURL = "http://10.0.2.2:8090/email?m=$email"

        val request = Request.Builder().url(emailURL).build()

        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call, response: Response) {

                val returnString = response.body()?.string()

                runOnUiThread {

                    if (returnString.equals("Succeeded")) {

                        val intent = Intent(this@EnterPasscodeActivity, EnterPasscodeActivity::class.java)
                        startActivity(intent)


                    } else {

                        val builder = AlertDialog.Builder(this@EnterPasscodeActivity)
                        builder.setTitle("Something went wrong.")
                        builder.setMessage("Please ensure you enter the email address correctly or try again later.")
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