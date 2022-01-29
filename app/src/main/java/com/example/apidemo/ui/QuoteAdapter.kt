package com.example.apidemo.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apidemo.data.local.model.Quote
import com.example.apidemo.databinding.QouteItemBinding

class QuoteAdapter: RecyclerView.Adapter<QuoteAdapter.QuoteViewHolder>() {



    private var data : ArrayList<Quote>? = null

    fun setData(list: ArrayList<Quote>){
        data = list
        notifyDataSetChanged()


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding : QouteItemBinding =
            QouteItemBinding.inflate(layoutInflater)
        return QuoteViewHolder(binding)
       // return QuoteViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.qoute_item,parent, false))

    }

    override fun getItemCount(): Int {
        return data?.size?: 0
    }

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        val item = data?.get(position)
        holder.bind(item)

    }



    class QuoteViewHolder(private val binding: QouteItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Quote?){

            binding.tvTitle.text = item?.author
            binding.tvBody.text = item?.title
        }

    }
}