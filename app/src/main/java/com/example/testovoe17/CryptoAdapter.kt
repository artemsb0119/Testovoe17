package com.example.testovoe17

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.text.DecimalFormat
import java.util.*

class CryptoAdapter(private val context: Context, val crypts: List<Crypto>) : RecyclerView.Adapter<CryptoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.crypto_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val crypto = crypts[position]
        holder.textViewTitle.text = crypto.title
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
        } else {
            val color = ContextCompat.getColor(holder.itemView.context, R.color.green)
            holder.textViewProc.setTextColor(color)
        }
        Glide.with(holder.itemView.context).load(crypto.image).into(holder.imageViewAvatar)
        Glide.with(holder.itemView.context).load(crypto.graf).into(holder.imageViewGraf)

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
        val textViewTitle: TextView = itemView.findViewById(R.id.textViewTitle)
        val textViewCost: TextView = itemView.findViewById(R.id.textViewCost)
        val textViewProc: TextView = itemView.findViewById(R.id.textViewProc)
        val imageViewGraf: ImageView = itemView.findViewById(R.id.imageViewGraf)
    }
}