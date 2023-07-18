package com.example.testovoe17

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.util.*

class CoinsAdapter(private val context: Context, val crypts: List<Crypto>) : RecyclerView.Adapter<CoinsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.all_items, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val crypto = crypts[position]
        holder.textViewTitle.text = crypto.title
        holder.textViewName.text = crypto.name
        holder.textViewId.text = (crypto.id + 1).toString()
        holder.textViewAll.text = crypto.all

        val initialCost = crypto.cost.toFloat()
        val minPercentage = 0.85f
        val maxPercentage = 1.15f
        val random = Random()
        val randomPercentage = minPercentage + random.nextFloat() * (maxPercentage - minPercentage)
        val newCost = initialCost * randomPercentage
        var formattedNewCost = String.format("%.${initialCost.toString().split(".")[1].length}f", newCost)
        if (crypto.id == 0) {
            formattedNewCost = formattedNewCost.replace(",", "")
        }
        holder.textViewCost.text = "$" + formattedNewCost
        holder.textViewProc.text = crypto.proc
        if (crypto.proc.contains("-")) {
            val color = ContextCompat.getColor(holder.itemView.context, R.color.red)
            holder.textViewProc.setTextColor(color)
            holder.textViewProc.setBackgroundResource(R.drawable.red_cons)
        } else {
            val color = ContextCompat.getColor(holder.itemView.context, R.color.green)
            holder.textViewProc.setTextColor(color)
            holder.textViewProc.setBackgroundResource(R.drawable.green_cons)
        }
        Glide.with(holder.itemView.context).load(crypto.image).into(holder.imageViewAvatar)
        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("crypto", crypto)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return crypts.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageViewAvatar: ImageView = itemView.findViewById(R.id.imageViewAvatar)
        val textViewName: TextView = itemView.findViewById(R.id.textViewName)
        val textViewTitle: TextView = itemView.findViewById(R.id.textViewTitle)
        val textViewId: TextView = itemView.findViewById(R.id.textViewId)
        val textViewCost: TextView = itemView.findViewById(R.id.textViewCost)
        val textViewAll: TextView = itemView.findViewById(R.id.textViewAll)
        val textViewProc: TextView = itemView.findViewById(R.id.textViewProc)
    }
}