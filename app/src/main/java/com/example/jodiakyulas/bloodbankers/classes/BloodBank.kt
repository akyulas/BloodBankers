package com.example.jodiakyulas.bloodbankers.classes

import java.io.Serializable

data class BloodBank(val _id : Int, val location: String, val address: String, val postalCode: String, val donationType: String,
                     val mondayOperatingHour: String, val tuesdayOperatingHour: String, val wednesdayOperatingHour: String,
                     val thursdayOperatingHour: String, val fridayOperatingHour: String, val saturdayOperatingHour: String,
                     val sundayOperatingHour: String, val eveOfMajorPublicHolidayOperatingHour: String, val publicHolidayOperatingHour: String)
    :Serializable{
}