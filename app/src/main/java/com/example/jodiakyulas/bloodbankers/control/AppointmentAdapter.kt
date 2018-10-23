package com.example.jodiakyulas.bloodbankers.control

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.jodiakyulas.bloodbankers.R
import com.example.jodiakyulas.bloodbankers.entity.Appointment
import com.example.jodiakyulas.bloodbankers.entity.AppointmentViewHolder


/**
 * This will create a controller class that will populate the appointment view.
 * @param items The list of appointments.
 * @param context The parent view.
 */
class AppointmentAdapter(var items: ArrayList<Appointment>, val context: Context) : RecyclerView.Adapter<AppointmentViewHolder>() {

    /**
     * This will bind the appointment view holder with information from items.
     * @param holder The appointment view holder that will be binded.
     * @param position The position of the items in the list of appointments.
     */
    override fun onBindViewHolder(holder: AppointmentViewHolder, position: Int) {
        holder.bloodbankLocation.text = items.get(position).location
        holder.bloodDonationType.text = items.get(position).donationType
        holder.bloodBankAddress.text = items.get(position).address
        holder.bloodBankPostalCode.text = items.get(position).postalCode
        holder.appointmentDate.text = items.get(position).appointmentDate
        holder.appointmentTime.text = items.get(position).appointmentTime
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
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppointmentViewHolder {
        return AppointmentViewHolder(LayoutInflater.from(context).inflate(R.layout.appointment_view, parent, false))
    }

    /**
     * Add new appointments to the list of appointments.
     * @param appointments The appointments that will be added.
     */
    fun setAppointments(appointments: ArrayList<Appointment>) {
        items.addAll(appointments)
        this.notifyDataSetChanged()
    }

}




