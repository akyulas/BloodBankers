package com.example.jodiakyulas.bloodbankers.entity

import java.io.Serializable

/**
 * The appointment class.
 * @param userMatricID The student's matric number.
 * @param location The location of the appointment.
 * @param donationType The type of donation that the student will do.
 * @param address The address of the appointment.
 * @param postalCode The postal code of the appointment.
 * @param appointmentDate The appointment date.
 * @param appointmentTime The appointment time.
 */
data class Appointment(val userMatricID: String, val location: String, val donationType: String, val address: String, val postalCode: String, val appointmentDate: String, val appointmentTime: String) :Serializable {

}