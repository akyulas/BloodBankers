package com.example.jodiakyulas.bloodbankers.control

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import com.example.jodiakyulas.bloodbankers.R
import com.example.jodiakyulas.bloodbankers.entity.OkHttpSingleton
import okhttp3.*
import java.io.IOException
import java.util.concurrent.TimeUnit

/**
 * This create a boundary class that lets the user recover password.
 */
class ForgetPasswordActivity: AppCompatActivity() {

    /**
     * Function that gets run on creation.
     * @param savedInstanceState The bundle saves the current instance of the activity.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_password)
    }

    /**
     * Function to reset password.
     */
    fun resetPassword(v: View) {
        val client = OkHttpSingleton.getClient()


        val email = findViewById<TextView>(R.id.forget_password_text_box).text.toString()

        val emailURL = "http://10.0.2.2:8090/email?m=$email"

        val request = Request.Builder().url(emailURL).build()

        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call, response: Response) {

                val returnString = response.body()?.string()

                runOnUiThread {

                    if (returnString.equals("Succeeded")) {
                        val sharedPreferences = getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE)
                        val editor = sharedPreferences.edit()
                        editor.putString("email", email)
                        editor.apply()

                        val intent = Intent(this@ForgetPasswordActivity, EnterPasscodeActivity::class.java)
                        startActivity(intent)


                    } else {

                        val builder = AlertDialog.Builder(this@ForgetPasswordActivity)
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