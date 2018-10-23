package com.example.jodiakyulas.bloodbankers.control

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jodiakyulas.bloodbankers.R
import android.widget.TextView
import com.example.jodiakyulas.bloodbankers.boundary.BloodBankDetailsActivity
import com.example.jodiakyulas.bloodbankers.entity.BloodBank
import com.example.jodiakyulas.bloodbankers.entity.BloodBankViewHolder

/**
 * This will create a controller class that will populate the blood bank view.
 * @param items The list of blood banks.
 * @param context The parent view.
 */
class BloodBankAdapter(var items: ArrayList<BloodBank>, val context: Context) : RecyclerView.Adapter<BloodBankViewHolder>() {

    /**
     * This will bind the blood bank view holder with information from items.
     * @param holder The blood bank view holder that will be binded.
     * @param position The position of the items in the list of blood banks.
     */
    override fun onBindViewHolder(holder: BloodBankViewHolder, position: Int) {
        holder.bloodbankLocation.text = items.get(position).location
        holder.bloodDonationType.text = items.get(position).donationType
        holder.bloodBankAddress.text = items.get(position).address
        holder.bloodBankPostalCode.text = items.get(position).postalCode

        holder.itemView.setOnClickListener(object: View.OnClickListener {
            override  fun onClick(v: View?) {
                val intent = Intent(context, BloodBankDetailsActivity::class.java)
                intent.putExtra("BloodBank", items.get(position))
                context.startActivity(intent)
            }
        })
    }

    /**
     * Return the number of blood banks.
     * @return The number of blood banks.
     */
    override fun getItemCount(): Int {
        return items.size
    }

    /**
     * Function that decides what happens when view holder is created.
     * @param parent The parent view.
     * @param viewType The view type.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BloodBankViewHolder {
        return BloodBankViewHolder(LayoutInflater.from(context).inflate(R.layout.blood_bank_view, parent, false))
    }

    /**
     * Add new blood banks to the list of blood banks.
     * @param bloodBanks The blood banks that will be added.
     */
    fun setBloodBanks(bloodBanks: ArrayList<BloodBank>) {
        items.addAll(bloodBanks)
        this.notifyDataSetChanged()
    }

}

