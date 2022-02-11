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
            binding.apply {
                concept.text = inOut.concept
                description.text = inOut.description
                price.text = inOut.formatPrice()
                kindOfMove.text = inOut.kindOfMove

                if (lastDate != inOut.date) {
                    dateTextView.text = inOut.date
                    lastDate = inOut.date
                } else {
                    dateTextView.visibility = View.GONE
                }

                imgCard.setImageResource(
                    when (inOut.description) {
                        "Transport" -> R.drawable.motorbike
                        "Food" -> R.drawable.food
                        "Clothes" -> R.drawable.clothes
                        "Market" -> R.drawable.market
                        else -> R.drawable.stuff
                    }
                )
                imgUpDown.setImageResource(
                    when (inOut.kindOfMove) {
                        "Incoming" -> R.drawable.up_arrow
                        else -> R.drawable.down_arrow
                    }
                )
                //holder to see full info
                mainLayout.setOnClickListener {
                }
            }
        }
    }
}