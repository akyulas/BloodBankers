package com.example.jodiakyulas.bloodbankers.control

import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.example.jodiakyulas.bloodbankers.entity.BloodBank
import java.lang.StringBuilder

/**
 * This creates a controller class that controls the logic for the appointment activity.
 */
class AppointmentController() {

    /**
     * Create a list of blood banks.
     * @param bloodBanksJsonString: The Json String that has been queried.
     * @return bloodBanks: The bloodbanks that has been created.
     */
    fun createBloodBankDataset(bloodBanksJsonString: String?): ArrayList<BloodBank> {
        var bloodBanks = ArrayList<BloodBank>()
        val parser: Parser = Parser()
        var json: JsonObject = parser.parse(StringBuilder(bloodBanksJsonString)) as JsonObject
        var tempString = json.get("result") as JsonObject
        var tempJson = parser.parse(StringBuilder(tempString.toJsonString())) as JsonObject
        var tempJsonArray  = tempJson.get("records") as JsonArray<JsonObject>
        for ((index, obj) in tempJsonArray.withIndex()) {
            bloodBanks.add(createBloodBank(obj))
        }
        return bloodBanks
    }

    /**
     * Create a single blood bank.
     * @param jsonObject: The Json Object that will be used to create blood banks.
     * @return BloodBank: The blood bank object that has been created.
     */
    fun createBloodBank(jsonObject: JsonObject) : BloodBank {
        val _id = jsonObject.get("_id").toString().toInt()
        val location = jsonObject.get("location").toString()
        val address = jsonObject.get("address").toString()
        val postalCode = jsonObject.get("postal_code").toString()
        val donationType = jsonObject.get("donation_type").toString()
        val mondayOperatingHour = jsonObject.get("monday_operating_hour").toString()
        val tuesdayOperatingHour = jsonObject.get("tuesday_operating_hour").toString()
        val wednesdayOperatingHour = jsonObject.get("wednesday_operating_hour").toString()
        val thursdayOperatingHour = jsonObject.get("thursday_operating_hour").toString()
        val fridayOperatingHour = jsonObject.get("friday_operating_hour").toString()
        val saturdayOperatingHour = jsonObject.get("saturday_operating_hour").toString()
        val sundayOperatingHour = jsonObject.get("sunday_operating_hour").toString()
        val eveOfMajorPublicHolidayOperatingHour = jsonObject.get("eve_of_major_public_holiday_operating_hour").toString()
        val publicHolidayOperatingHour = jsonObject.get("public_holiday_operating_hour").toString()
        return BloodBank(_id = _id, location = location, address = address, postalCode = postalCode, donationType = donationType,
                mondayOperatingHour = mondayOperatingHour, tuesdayOperatingHour =  tuesdayOperatingHour, wednesdayOperatingHour = wednesdayOperatingHour,
                thursdayOperatingHour = thursdayOperatingHour, fridayOperatingHour = fridayOperatingHour, saturdayOperatingHour = saturdayOperatingHour,
                sundayOperatingHour = sundayOperatingHour, eveOfMajorPublicHolidayOperatingHour = eveOfMajorPublicHolidayOperatingHour, publicHolidayOperatingHour = publicHolidayOperatingHour)
    }



}