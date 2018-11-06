package com.example.jodiakyulas.bloodbankers.control

import android.content.Context
import android.content.Intent
import android.support.v7.app.AlertDialog
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.jodiakyulas.bloodbankers.R
import com.example.jodiakyulas.bloodbankers.entity.MerchantViewHolder
import com.example.jodiakyulas.bloodbankers.entity.ParticipatingMerchants

class MerchantAdapter(var items: ArrayList<ParticipatingMerchants>, val context: Context) : RecyclerView.Adapter<MerchantViewHolder>() {


    override fun onBindViewHolder(holder: MerchantViewHolder, position: Int) {
        holder.merchantName.text = items.get(position).merchantName
        holder.merchantText.text = items.get(position).merchantText
        holder.merchantPoints.text = items.get(position).merchantPoints.toString()

        holder.itemView.setOnClickListener(object: View.OnClickListener {
            override  fun onClick(v: View?) {
                val sharedPreferences = context.getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE)

                val userPoints = sharedPreferences.getInt("points", -1)

                if (userPoints < items.get(position).merchantPoints) {
                    val builder = AlertDialog.Builder(context)
                    builder.setTitle("Not enough points.")
                    builder.setMessage("You do not have enough points to choose this promotion.")
                    val dialog: AlertDialog = builder.create()
                    dialog.show()
                } else {
                    val intent = Intent(context, RedemptionDetailActivity::class.java)
                    intent.putExtra("ParticipatingMerchants", items.get(position))
                    context.startActivity(intent)
                }
            }
        })
    }




    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MerchantViewHolder {
        return MerchantViewHolder(LayoutInflater.from(context).inflate(R.layout.merchant_view, parent, false))
    }

    fun setAppointments(participatingMerchants: ArrayList<ParticipatingMerchants>) {
        items.addAll(participatingMerchants)
        this.notifyDataSetChanged()
    }

}