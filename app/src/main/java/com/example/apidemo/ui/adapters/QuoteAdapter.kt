package com.example.apidemo.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apidemo.R
import com.example.apidemo.data.local.model.Quote


class QuoteAdapter: RecyclerView.Adapter<QuoteAdapter.QuoteViewHolder>() {
    private var data : ArrayList<Quote>? = null

    fun setData(list: ArrayList<Quote>){
        data = list
        notifyDataSetChanged()

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        return QuoteViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.qoute_item,parent, false))

    }
    override fun getItemCount(): Int {
        return data?.size?: 0
    }

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        val item = data?.get(position)
        holder.bind(item)
    }

    class QuoteViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        fun bind(item: Quote?){
            itemView.findViewById<TextView>(R.id.tv_body).text = item?.author
            itemView.findViewById<TextView>(R.id.tv_title).text = item?.title
           // binding.tvBody.text = item?.author
           // binding.tvTitle.text = item?.title
        }
    }
}