package com.example.testovoe17

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RoundAdapter(val rounds: List<Round>) : RecyclerView.Adapter<RoundAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.round_items, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val round = rounds[position]
        holder.textViewData.text = round.data
        holder.textViewFirma.text = round.firma
        holder.textViewTitle.text = round.title
        holder.textViewRaise.text = round.raised
        holder.textViewRaiseResult.text = round.raised_result
        holder.textViewCategory.text = round.investors
        Glide.with(holder.itemView.context).load(round.avatar).into(holder.imageViewAvatar)
    }

    override fun getItemCount(): Int {
        return rounds.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewData: TextView = itemView.findViewById(R.id.textViewData)
        val textViewFirma: TextView = itemView.findViewById(R.id.textViewFirma)
        val imageViewAvatar: ImageView = itemView.findViewById(R.id.imageViewAvatar)
        val textViewTitle: TextView = itemView.findViewById(R.id.textViewTitle)
        val textViewRaise: TextView = itemView.findViewById(R.id.textViewRaise)
        val textViewRaiseResult: TextView = itemView.findViewById(R.id.textViewRaiseResult)
        val textViewCategory: TextView = itemView.findViewById(R.id.textViewInvestors)
    }
}