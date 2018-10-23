package com.example.jodiakyulas.bloodbankers.boundary

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.jodiakyulas.bloodbankers.R
import com.example.jodiakyulas.bloodbankers.control.MobileBloodDriveController

/**
 * This create a boundary class that lets the user view the mobile blood drives.
 */
class MobileBloodDriveActivity: AppCompatActivity() {

    /**
     * Instantiate the mobile blood drive controller.
     */
    val mobileBloodDriveController = MobileBloodDriveController(this)

    /**
     * Function that gets run on creation.
     * @param savedInstanceState The bundle saves the current instance of the activity.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mobileBloodDriveController.populateMobileBloodDrive()
    }

}