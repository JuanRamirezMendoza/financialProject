package com.financialproject.financialproject.ui.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.financialproject.financialproject.R
import com.financialproject.financialproject.data.extensionfunctions.formatPrice
import com.financialproject.financialproject.data.model.InOut
import com.financialproject.financialproject.databinding.ViewInOutBinding

class MenuAdapter(
    private var list: List<InOut> = mutableListOf()
) : RecyclerView.Adapter<MenuAdapter.ViewHolder>() {

    private var lastDate = ""

    @SuppressLint("NotifyDataSetChanged")
    fun addData(list: List<InOut>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ViewInOutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(val binding: ViewInOutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(inOut: InOut) {
            binding.concept.text = inOut.concept
            binding.description.text = inOut.description
            binding.price.text = inOut.formatPrice()
            binding.kindOfMove.text = inOut.kindOfMove

            if (lastDate != inOut.date) {
                binding.dateTextView.text = inOut.date
                lastDate = inOut.date
            } else {
                binding.dateTextView.visibility = View.GONE
            }

            when (inOut.description) {
                "Transport" -> binding.imgCard.setImageResource(R.drawable.motorbike)
                "Food" -> binding.imgCard.setImageResource(R.drawable.food)
                "Clothes" -> binding.imgCard.setImageResource(R.drawable.clothes)
                "Market" -> binding.imgCard.setImageResource(R.drawable.market)
                else -> binding.imgCard.setImageResource(R.drawable.stuff)
            }
            when (inOut.kindOfMove) {
                "Incoming" -> binding.imgUpDown.setImageResource(R.drawable.up_arrow)
                "Expenses" -> binding.imgUpDown.setImageResource(R.drawable.down_arrow)
            }
            //holder to see full info
            binding.mainLayout.setOnClickListener {
            }
        }
    }
}