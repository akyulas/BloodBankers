package com.example.jodiakyulas.bloodbankers.control

/**
 * This create a control class for log in purposes.
 */
class LoginController() {

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

}