package com.example.jodiakyulas.bloodbankers.control

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.jodiakyulas.bloodbankers.R
import com.example.jodiakyulas.bloodbankers.entity.Appointment
import com.example.jodiakyulas.bloodbankers.entity.AppointmentViewHolder
import com.example.jodiakyulas.bloodbankers.entity.MobileBloodBank
import com.example.jodiakyulas.bloodbankers.entity.MobileBloodBankViewHolder

/**
 * This will create a controller class that will populate the mobile blood bank view.
 * @param items The list of appointments.
 * @param context The parent view.
 */
class MobileBloodBankAdapter(var items: ArrayList<MobileBloodBank>, val context: Context) : RecyclerView.Adapter<MobileBloodBankViewHolder>() {

    /**
     * This will bind the appointment view holder with information from items.
     * @param holder The appointment view holder that will be binded.
     * @param position The position of the items in the list of appointments.
     */
    override fun onBindViewHolder(holder: MobileBloodBankViewHolder, position: Int) {
        holder.bloodBankLocation.text = items.get(position).location
        populateLayOutView(holder, items.get(position).datesAndTimes)
        holder.bloodBankAddress.text = items.get(position).address
        holder.bloodBankGoogleLocation.setOnClickListener{
            val mapURL = "http://maps.google.com.sg/maps?q=${holder.bloodBankAddress.text}"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(mapURL))
            context.startActivity(intent)
        }
    }

    /**
     * Return the number of appointments.
     * @return The number of appointments
     */
    override fun getItemCount(): Int {
        return items.size
    }

    /**
     * Function that decides what happens when view holder is created.
     * @param parent The parent view.
     * @param viewType The view type.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MobileBloodBankViewHolder {
        return MobileBloodBankViewHolder(LayoutInflater.from(context).inflate(R.layout.mobile_blood_drive_view, parent, false))
    }

    /**
     * Add new mobile blood banks to the list of mobile bloodbanks.
     * @param appointments The appointments that will be added.
     */
    fun setBloodBanks(mobileBloodBanks: ArrayList<MobileBloodBank>) {
        items.addAll(mobileBloodBanks)
        this.notifyDataSetChanged()
    }

    /**
     *
     */
    fun populateLayOutView(holder: MobileBloodBankViewHolder, datesAndTimes: ArrayList<MobileBloodBank.MobileDriveDateAndTime>) {
        for (dateAndTime in datesAndTimes) {
            val dateTextView = TextView(context)
            dateTextView.text = dateAndTime.driveDate
            holder.bloodBankTimings.addView(dateTextView)
            val timeTextView = TextView(context)
            timeTextView.text = dateAndTime.timings
            holder.bloodBankTimings.addView(timeTextView)
        }
    }



}