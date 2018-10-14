package com.example.jodiakyulas.bloodbankers.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.example.jodiakyulas.bloodbankers.R
import com.example.jodiakyulas.bloodbankers.classes.LoginUserAuthenticator
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException

class LoginActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

    }

    fun loadRegistrationPage(v: View) {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

    fun authenticationForLogin(v: View) {
        val username = findViewById<EditText>(R.id.editLoginUsername).text.toString()
        val password = findViewById<EditText>(R.id.editPassword).text.toString()
        val loginUser = LoginUserAuthenticator(username, password)

        val json = Gson().toJson(loginUser)
        val body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json)
        val client = OkHttpClient()
        val url = "http://10.0.2.2:8090/login"
        val request = Request.Builder().url(url).post(body).build()
        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call, response: Response) {

                runOnUiThread {
                    val responseMessage = response.body()?.string().toString()
                    if (responseMessage.equals("Log in information is false")) {
                        val builder = AlertDialog.Builder(this@LoginActivity)
                        builder.setTitle("Login Failed.")
                        builder.setMessage("Please ensure you have key in your particulars correctly.")
                        val dialog: AlertDialog = builder.create()
                        dialog.show()
                    } else if (responseMessage.equals("An error occured")) {
                        val builder = AlertDialog.Builder(this@LoginActivity)
                        builder.setTitle("An error occured.")
                        builder.setMessage("Please try to log in again later.")
                        val dialog: AlertDialog = builder.create()
                        dialog.show()
                    } else if (RegisterActivity().matchMatricCardNo(responseMessage)){
                        val sharedPreferences = getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE)
                        val editor = sharedPreferences.edit()
                        editor.putBoolean("hasLoggedIn", true);
                        editor.putString("matricID", responseMessage)
                        editor.apply()
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        findViewById<TextView>(R.id.editLoginUsername).text = responseMessage
                    }
                }

            }

            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

        })
    }



}