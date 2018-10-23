package com.example.jodiakyulas.bloodbankers.control

import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.example.jodiakyulas.bloodbankers.entity.Appointment
import java.lang.StringBuilder
import java.text.SimpleDateFormat
import java.util.*

/**
 * This creates a controller class that controls the logic for view the current appointment.
 */
class ViewCurrentAppointmentController() {

    /**
     * Get the current appointment.
     * @param appointmentString The json appointment string returned by the backend server.
     * @return The current appointment.
     */
    fun getCurrentAppointment(appointmentString: String?) : Appointment?{
        if (appointmentString == null) {
            return null
        }
        val parser: Parser = Parser()
        var json: JsonArray<JsonObject> = parser.parse(StringBuilder(appointmentString)) as JsonArray<JsonObject>
        val jsonObj = json.get(0)

        val appointmentDate = jsonObj.string("appointmentDate").toString()

        val appointmentTime = jsonObj.string("appointmentTime").toString()

        val timings = appointmentTime.split(" - ".toRegex())
        val endTime = timings.get(1)

        val appointmentEndDate = "${appointmentDate} ${endTime}"

        val pattern = "dd.MM.yyyy HH:mm"
        val endingDate = SimpleDateFormat(pattern).parse(appointmentEndDate)

        if (endingDate.after(Calendar.getInstance().time)) {
            val userMatricID = jsonObj.string("userMatricID").toString()
            val location = jsonObj.string("location").toString()
            val donationType = jsonObj.string("donationType").toString()
            val address = jsonObj.string("address").toString()
            val postalCode = jsonObj.string("postalCode").toString()
            return Appointment(userMatricID, location, donationType, address, postalCode, appointmentDate, appointmentTime)
        } else {
            return null
        }

    }


}