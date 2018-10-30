package com.example.jodiakyulas.bloodbankers.entity

/**
 * Class representing mobile blood banks.
 * @param location The location of the mobile blood bank.
 * @param dateAndTime The date and time of the mobile blood bank.
 * @param address: The address of the mobile blood bank.
 */
data class MobileBloodBank(val location: String, val datesAndTimes: ArrayList<MobileDriveDateAndTime>, val address: String) {


    /**
     * Class representing date and time for mobile blood banks.
     * @param driveDate The date of the mobile blood drive.
     * @param timings The timing of the mobile blood drive.
     */
    data class MobileDriveDateAndTime(val driveDate: String, val timings: String) {

    }

}