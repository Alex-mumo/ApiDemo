package com.example.apidemo.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.apidemo.MainActivity
import com.example.apidemo.R
import com.example.apidemo.data.local.model.Quote
import com.example.apidemo.databinding.QouteItemBinding
import kotlin.text.Typography.quote

class QuoteAdapter constructor(private val quotes: MutableList<Quote>) :
    RecyclerView.Adapter<QuoteAdapter.QuoteViewHolder>() {



    //private var data : ArrayList<Quote>? = null

    //fun setData(list: ArrayList<Quote>){
      //  data = list
        //notifyDataSetChanged()


    //}


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding : QouteItemBinding =
            QouteItemBinding.inflate(layoutInflater)
        return QuoteViewHolder(binding)
       // return QuoteViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.qoute_item,parent, false))

    }

    override fun getItemCount() = quotes.size

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        val quotes = quotes[position]
        holder.bind(holder.itemView.context, quotes)
        //val item = data?.get(position)
        //holder.bind()

    }



    class QuoteViewHolder(private val binding: QouteItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(context: Context, quote: Quote){

            binding.tvTitle.text = quote.body
            binding.tvBody.text = quote.title
        }

    }
}