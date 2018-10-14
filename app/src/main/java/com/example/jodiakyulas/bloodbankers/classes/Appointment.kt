package com.example.jodiakyulas.bloodbankers.classes

import java.io.Serializable

data class Appointment(val userMatricID: String, val location: String, val donationType: String, val address: String, val postalCode: String, val appointmentDate: String, val appointmentTime: String) :Serializable {

}