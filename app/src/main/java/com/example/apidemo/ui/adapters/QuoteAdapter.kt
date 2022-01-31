package com.example.apidemo.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.apidemo.R
import com.example.apidemo.data.local.model.Quote
import com.example.apidemo.databinding.QouteItemBinding


class QuoteAdapter: RecyclerView.Adapter<QuoteAdapter.QuoteViewHolder>() {

    class QuoteViewHolder(private val binding: QouteItemBinding):
        RecyclerView.ViewHolder(binding.root) {
            fun bind(context: Context, quote: Quote){
                binding.rvqouteTv.text = quote.title
                binding.rvauthorTv.text = quote.author
            }
    }



    // inner class QuoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    private val differCallBack = object : DiffUtil.ItemCallback<Quote>() {

        override fun areItemsTheSame(oldItem: Quote, newItem: Quote): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: Quote, newItem: Quote): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: QouteItemBinding =
            QouteItemBinding.inflate(layoutInflater)
        return QuoteViewHolder(binding)
        /*return QuoteViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.qoute_item,
                    parent,
                    false
                )
        )*/

    }
    override fun getItemCount(): Int {
        return differ.currentList.size

    }

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        val quote = differ.currentList[position]

        holder.bind(holder.itemView.context, quote)



    }
}

