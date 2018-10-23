package com.example.jodiakyulas.bloodbankers.entity

import java.io.Serializable

/**
 * The class for the blood bank.
 * @param _id The id of the blood bank in the api.
 * @param location The location of the blood bank.
 * @param address The address of the blood bank.
 * @param postalCode The postal code of the blood bank.
 * @param donationType The donation type accepted by the blood bank.
 * @param mondayOperatingHour The operating hour on monday.
 * @param tuesdayOperatingHour The operating hour on tuesday.
 * @param wednesdayOperatingHour The operating hour on wednesday.
 * @param thursdayOperatingHour The operating hour on thursday.
 * @param fridayOperatingHour The operating hour on friday.
 * @param saturdayOperatingHour The operating hour on saturday.
 * @param sundayOperatingHour The operating hour on sunday.
 * @param eveOfMajorPublicHolidayOperatingHour The operating hour on eve of major public holiday.
 * @param publicHolidayOperatingHour The operating hour on public holiday.
 */
data class BloodBank(val _id : Int, val location: String, val address: String, val postalCode: String, val donationType: String,
                     val mondayOperatingHour: String, val tuesdayOperatingHour: String, val wednesdayOperatingHour: String,
                     val thursdayOperatingHour: String, val fridayOperatingHour: String, val saturdayOperatingHour: String,
                     val sundayOperatingHour: String, val eveOfMajorPublicHolidayOperatingHour: String, val publicHolidayOperatingHour: String)
    :Serializable{
}