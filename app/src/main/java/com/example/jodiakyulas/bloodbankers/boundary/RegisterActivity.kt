package com.example.jodiakyulas.bloodbankers.boundary

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import com.example.jodiakyulas.bloodbankers.R
import com.example.jodiakyulas.bloodbankers.control.RegisterController
import com.example.jodiakyulas.bloodbankers.entity.User
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException

/**
 * This create a boundary class that lets the user register.
 */
class RegisterActivity : AppCompatActivity(){

    /**
     * Instantiate the register controller.
     */
    val registerController = RegisterController(this)


    /**
     * Function that gets run on creation.
     * @param savedInstanceState The bundle saves the current instance of the activity.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        registerController.prepareMatriculationField()
        registerController.prepareEmailField()
        registerController.preparePasswordField()
        registerController.prepareSecondPasswordField()

    }

    /**
     * Show the user dialog for success.
     */
    fun showSuccess() {
        val builder = AlertDialog.Builder(this@RegisterActivity)
        builder.setTitle("Registration successful.")
        builder.setMessage("Your account has been registered. Please log in using the credentials that you have provided.")
        val dialog: AlertDialog = builder.create()
        dialog.show()
        goToLoginPage()
    }

    /**
     * Function to load the login page.
     */
    fun goToLoginPage() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    /**
     * Function to help to register the user.
     * @param v: The view that the user selected.
     */
    fun registerUser(v: View) {
        if (!registerController.matricNoFilledInAndValid() || !registerController.emailFilledInAndValid() || !registerController.passwordFilledAndValid() || !registerController.password2FilledAndValid()) {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Please enter valid information.")
            builder.setMessage("Please ensure you have entered all the fields correctly and all the fields are filled.")
            val dialog: AlertDialog = builder.create()
            dialog.show()
            return
        }
        val matricNo = findViewById<EditText>(R.id.editUsername).text.toString()
        val email = findViewById<EditText>(R.id.editUsername2).text.toString()
        val password = findViewById<EditText>(R.id.editPassword).text.toString()
        val user = User(matricNo, email, password, 0)

        val json = Gson().toJson(user)
        val body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json)
        val client = OkHttpClient()
        val url = "http://10.0.2.2:8090/register"
        val request = Request.Builder().url(url).post(body).build()
        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call, response: Response) {

                runOnUiThread {
                    showSuccess()
                }

            }

            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

        })
    }


}