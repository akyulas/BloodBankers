package com.example.jodiakyulas.bloodbankers.control

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.example.jodiakyulas.bloodbankers.R
import okhttp3.*
import java.io.IOException

/**
 * This create a controller class that lets the user change password.
 */
class ChangePasswordActivity: AppCompatActivity() {

    /**
     * Function that gets run on creation.
     * @param savedInstanceState The bundle saves the current instance of the activity.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        preparePasswordField()
        prepareSecondPasswordField()
    }

    /**
     * Function that will take care of password change.
     */
    fun changePassword(v: View) {
        if (!passwordFilledAndValid() || !password2FilledAndValid()) {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Please enter valid information.")
            builder.setMessage("Please ensure you have entered all the fields correctly and all the fields are filled.")
            val dialog: AlertDialog = builder.create()
            dialog.show()
        } else {
            val password = findViewById<EditText>(R.id.editPassword).text.toString()
            val client = OkHttpClient()

            val sharedPreferences = getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE)
            val email = sharedPreferences.getString("email", "wrong-email")

            val passcodeURL = "http://10.0.2.2:8090/password/$email/$password"

            val request = Request.Builder().url(passcodeURL).build()

            client.newCall(request).enqueue(object: Callback {
                override fun onResponse(call: Call, response: Response) {

                    val returnString = response.body()?.string()

                    runOnUiThread {

                        if (returnString.equals("Succeeded")) {

                            val intent = Intent(this@ChangePasswordActivity, LoginActivity::class.java)
                            startActivity(intent)


                        } else {

                            val builder = AlertDialog.Builder(this@ChangePasswordActivity)
                            builder.setTitle("Something went wrong.")
                            builder.setMessage(returnString)
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

    fun prepareSecondPasswordField() {
        val passwordField = findViewById<EditText>(R.id.editPassword)
        val secondPasswordField = findViewById<EditText>(R.id.editPassword2)
        secondPasswordField.addTextChangedListener( object: TextWatcher {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                val password2 = p0.toString()
                val password = passwordField.text.toString()
                if (password.equals(password2)) secondPasswordField.setError(null) else secondPasswordField.setError("The two passwords must be the same.")
                if (password2 == "") secondPasswordField.setError(null)
            }

        })
    }

    /**
     * This is used to prepare the first password field.
     */
    fun preparePasswordField() {
        val passwordField = findViewById<EditText>(R.id.editPassword)
        passwordField.addTextChangedListener( object: TextWatcher {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                val password = p0.toString()
                if (matchPassword(password)) passwordField.setError(null) else passwordField.setError("Password must be at least 8 characters, a lower case and an upper case alphabet, and a number.")
                if (password == "") passwordField.setError(null)
            }

        })
    }

    /**
     * This is used to match password with a specific regex.
     * @param password The password.
     * @return Boolean that shows the result of the matching.
     */
    fun matchPassword(password: String): Boolean {
        val upperCaseRegex = """^.*[A-Z].*$""".toRegex()
        val lowerCaseRegex = """^.*[a-z].*$""".toRegex()
        val numberRegex = """^.*[0-9].*$""".toRegex()
        if (password.length < 8)
            return false
        if (!upperCaseRegex.matches(password))
            return false
        if (!lowerCaseRegex.matches(password))
            return false
        if (!numberRegex.matches(password))
            return false
        return true
    }

    /**
     * Function to check if password has been filled in and that it it valid.
     * @return Boolean that shows the result of the matching.
     */
    fun passwordFilledAndValid(): Boolean {
        val passwordField = findViewById<EditText>(R.id.editPassword)
        val password = passwordField.text.toString()
        if (password != "" && matchPassword(password))
            return true
        return false
    }

    /**
     * Function to check if second password equals first password.
     * @return Boolean that shows the result of the matching.
     */
    fun password2FilledAndValid(): Boolean {
        val passwordField = findViewById<EditText>(R.id.editPassword)
        val password = passwordField.text.toString()
        val passwordField2 = findViewById<EditText>(R.id.editPassword2)
        val password2 = passwordField2.text.toString()
        if (password != "" && password.equals(password2))
            return true
        return false
    }

}