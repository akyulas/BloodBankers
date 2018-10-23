package com.example.jodiakyulas.bloodbankers.entity

/**
 * The user class.
 * @param matricNo The matric number of the student.
 * @param email The email of the student.
 * @param password The password of the student.
 * @param points The points acquired by the student.
 */
data class User(val matricNo: String, val email: String, val password: String, val points: Int) {

}