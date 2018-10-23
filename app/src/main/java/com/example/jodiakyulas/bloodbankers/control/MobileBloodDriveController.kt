package com.example.jodiakyulas.bloodbankers.control

import android.app.Activity

/**
 * This create a control class that lets the user view mobile blood drives.
 * @param activity The mobile blood drive activity.
 */
class MobileBloodDriveController(val activity: Activity) {

    /**
     * Function to populate with the mobile blood drives.
     */
    fun populateMobileBloodDrive() {
        scrapeWebPage()
        parseXML("")
        populate()
    }

    /**
     * Function to scrape the web page.
     */
    fun scrapeWebPage() {

    }

    /**
     * Function to parse the xml.
     * @xml The xml string that will be parsed through.
     */
    fun parseXML(xml: String) {

    }

    /**
     * Function to start populating with the mobile blood drives.
     */
    fun populate() {

    }
}