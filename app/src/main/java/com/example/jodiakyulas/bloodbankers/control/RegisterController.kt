package com.example.jodiakyulas.bloodbankers.control

import android.app.Activity
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import com.example.jodiakyulas.bloodbankers.R

/**
 * This create a control class for registration purposes.
 * @param activity The activity for registration.
 */
class RegisterController(val activity: Activity) {

    /**
     * This is used to prepare the second password field.
     */
    fun prepareSecondPasswordField() {
        val passwordField = activity.findViewById<EditText>(R.id.editPassword)
        val secondPasswordField = activity.findViewById<EditText>(R.id.editPassword2)
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
        val passwordField = activity.findViewById<EditText>(R.id.editPassword)
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
     * This is used to prepare the email field.
     */
    fun prepareEmailField() {
        val emailField = activity.findViewById<EditText>(R.id.editUsername2)
        emailField.addTextChangedListener( object: TextWatcher {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                val email = p0.toString()
                if (matchEmail(email)) emailField.setError(null) else emailField.setError("E-mail must be in the format userid@e.ntu.edu.sg")
                if (email == "") emailField.setError(null)
            }

        })
    }

    /**
     * This is used to preprare the matriculation card field.
     */
    fun prepareMatriculationField() {
        val matricField = activity.findViewById<EditText>(R.id.editUsername)
        matricField.addTextChangedListener( object: TextWatcher {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                val matriculationNumber = p0.toString()
                if (matchMatricCardNo(matriculationNumber)) matricField.setError(null) else matricField.setError("Matriculation number must be in the format U/GXXXXXXXY")
                if (matriculationNumber == "") matricField.setError(null)
            }

        })
    }

    /**
     * This is used to match matriculation card number with a specific regex.
     * @param cardNo The matriculation card no.
     * @return Boolean that shows the result of the matching.
     */
    fun matchMatricCardNo(cardNo: String): Boolean {
        val regex = """^(U|G)[0-9]{7}[A-Z]$""".toRegex()
        if (regex.matches(cardNo))
            return true
        return false
    }

    /**
     * This is used to match email address with a specific regex.
     * @param email The email address.
     * @return Boolean that shows the result of the matching.
     */
    fun matchEmail(email: String): Boolean {
        val regex = """^[a-zA-Z0-9_\.+]+@(e)(\.ntu)(\.edu)(\.sg)$""".toRegex()
        if (regex.matches(email))
            return true
        return false
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
     * Function to check if matriculation number has been filled in and that it it valid.
     * @return Boolean that shows the result of the matching.
     */
    fun matricNoFilledInAndValid(): Boolean {
        val matricField = activity.findViewById<EditText>(R.id.editUsername)
        val matricId = matricField.text.toString()
        if (matricId != "" && matchMatricCardNo(matricId))
            return true
        return false
    }

    /**
     * Function to check if email address has been filled in and that it it valid.
     * @return Boolean that shows the result of the matching.
     */
    fun emailFilledInAndValid(): Boolean {
        val emailField = activity.findViewById<EditText>(R.id.editUsername2)
        val email = emailField.text.toString()
        if (email != "" && matchEmail(email))
            return true
        return false
    }

    /**
     * Function to check if password has been filled in and that it it valid.
     * @return Boolean that shows the result of the matching.
     */
    fun passwordFilledAndValid(): Boolean {
        val passwordField = activity.findViewById<EditText>(R.id.editPassword)
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
        val passwordField = activity.findViewById<EditText>(R.id.editPassword)
        val password = passwordField.text.toString()
        val passwordField2 = activity.findViewById<EditText>(R.id.editPassword2)
        val password2 = passwordField2.text.toString()
        if (password != "" && password.equals(password2))
            return true
        return false
    }

}