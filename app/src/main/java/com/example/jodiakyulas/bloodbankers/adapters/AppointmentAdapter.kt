package com.example.jodiakyulas.bloodbankers.adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.jodiakyulas.bloodbankers.R
import com.example.jodiakyulas.bloodbankers.activities.BloodBankDetailsActivity
import com.example.jodiakyulas.bloodbankers.classes.Appointment

class AppointmentAdapter(var items: ArrayList<Appointment>, val context: Context) : RecyclerView.Adapter<AppointmentViewHolder>() {

    override fun onBindViewHolder(holder: AppointmentViewHolder, position: Int) {
        holder.bloodbankLocation.text = items.get(position).location
        holder.bloodDonationType.text = items.get(position).donationType
        holder.bloodBankAddress.text = items.get(position).address
        holder.bloodBankPostalCode.text = items.get(position).postalCode
        holder.appointmentDate.text = items.get(position).appointmentDate
        holder.appointmentTime.text = items.get(position).appointmentTime
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppointmentViewHolder {
        return AppointmentViewHolder(LayoutInflater.from(context).inflate(R.layout.appointment_view, parent, false))
    }

    fun setAppointments(appointments: ArrayList<Appointment>) {
        items.addAll(appointments)
        this.notifyDataSetChanged()
    }

    fun getAppointment(position: Int) : Appointment{
        return items.get(position)
    }

}

class AppointmentViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    var bloodbankLocation: TextView = view.findViewById(R.id.bloodBankLocation)
    var bloodDonationType: TextView = view.findViewById(R.id.bloodBankDonationType)
    var bloodBankAddress: TextView = view.findViewById(R.id.bloodBankAddress)
    var bloodBankPostalCode: TextView = view.findViewById(R.id.bloodBankPostalCode)
    var appointmentDate: TextView = view.findViewById(R.id.bloodBankAppointmentDate)
    var appointmentTime: TextView = view.findViewById(R.id.bloodBankAppointmentTime)
}

