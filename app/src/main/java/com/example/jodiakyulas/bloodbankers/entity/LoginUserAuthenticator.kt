package com.example.jodiakyulas.bloodbankers.entity

/**
 * The class that will be passed to the backend server for login authentication.
 * @param userName The username of the student.
 * @param password The password of the student.
 */
data class LoginUserAuthenticator(val userName: String, val password: String) {

}