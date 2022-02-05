package com.financialproject.financialproject.ui.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.financialproject.financialproject.R
import com.financialproject.financialproject.data.extensionfunctions.formatNumber
import com.financialproject.financialproject.data.model.InOut
import com.financialproject.financialproject.databinding.ViewInOutBinding

class MenuAdapter(
    private var list: List<InOut> = mutableListOf()
) : RecyclerView.Adapter<MenuAdapter.ViewHolder>() {

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
            binding.price.text = list.formatNumber(inOut.price)
            binding.kindOfMove.text = inOut.kindOfMove
            binding.imgCard.setImageResource(R.drawable.add)

            //holder to see full info
            binding.mainLayout.setOnClickListener {
            }
        }
    }
}