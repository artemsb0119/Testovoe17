package com.example.testovoe17

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class TokenAdapter(val tokens: List<Token>) : RecyclerView.Adapter<TokenAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.token_items, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val token = tokens[position]
        holder.textViewData.text = token.data
        holder.textViewFirma.text = token.firma
        holder.textViewTitle.text = token.title
        holder.textViewText.text = token.text
        holder.textViewRaise.text = token.raise
        holder.textViewRaiseResult.text = token.raise_result
        holder.textViewCategory.text = token.category
        holder.textViewCategoryResult.text = token.category_result
        Glide.with(holder.itemView.context).load(token.avatar).into(holder.imageViewAvatar)
    }

    override fun getItemCount(): Int {
        return tokens.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewData: TextView = itemView.findViewById(R.id.textViewData)
        val textViewFirma: TextView = itemView.findViewById(R.id.textViewFirma)
        val imageViewAvatar: ImageView = itemView.findViewById(R.id.imageViewAvatar)
        val textViewTitle: TextView = itemView.findViewById(R.id.textViewTitle)
        val textViewText: TextView = itemView.findViewById(R.id.textViewText)
        val textViewRaise: TextView = itemView.findViewById(R.id.textViewRaise)
        val textViewRaiseResult: TextView = itemView.findViewById(R.id.textViewRaiseResult)
        val textViewCategory: TextView = itemView.findViewById(R.id.textViewCategory)
        val textViewCategoryResult: TextView = itemView.findViewById(R.id.textViewCategoryResult)
    }
}