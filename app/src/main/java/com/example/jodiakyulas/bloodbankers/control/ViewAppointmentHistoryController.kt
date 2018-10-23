package com.example.jodiakyulas.bloodbankers.control

import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.example.jodiakyulas.bloodbankers.entity.Appointment
import java.lang.StringBuilder
import java.util.ArrayList

/**
 * This creates a controller class for the appointment history logic.
 */
class ViewAppointmentHistoryController() {

    /**
     * Get the appointment history.
     * @param appointmentString The json appointments string returned by the backend server.
     * @return Array List of Appointments.
     */
    fun getAppointmentHistory(appointmentString: String?) : ArrayList<Appointment>?{
        if (appointmentString == null) {
            return null
        }
        val parser: Parser = Parser()
        var json: JsonArray<JsonObject> = parser.parse(StringBuilder(appointmentString)) as JsonArray<JsonObject>

        val appointments = ArrayList<Appointment>()

        for (i in 0..(json.size - 1)) {
            val jsonObj = json.get(i)

            val userMatricID = jsonObj.string("userMatricID").toString()
            val location = jsonObj.string("location").toString()
            val donationType = jsonObj.string("donationType").toString()
            val address = jsonObj.string("address").toString()
            val postalCode = jsonObj.string("postalCode").toString()

            val appointmentDate = jsonObj.string("appointmentDate").toString()

            val appointmentTime = jsonObj.string("appointmentTime").toString()

            appointments.add(Appointment(userMatricID, location, donationType, address, postalCode, appointmentDate, appointmentTime))
        }

        return appointments

    }

}