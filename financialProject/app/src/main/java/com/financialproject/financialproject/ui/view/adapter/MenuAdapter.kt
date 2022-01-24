package com.financialproject.financialproject.ui.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.financialproject.financialproject.R
import com.financialproject.financialproject.data.model.InOut

class MenuAdapter(
    private val context: Context,
    private val list: List<InOut>,
    val activity: AppCompatActivity
) : RecyclerView.Adapter<MenuAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.view_in_out, parent, false)
        return MyViewHolder(view)
    }

    @SuppressLint("SimpleDateFormat")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val inOut = list[position]
        holder.concept.text = inOut.concept
        holder.description.text = inOut.description
        holder.price.text = inOut.price
        holder.kindOfMove.text = inOut.kindOfMove

        //holder to see full info
        /*holder.mainLayout.setOnClickListener {
            val intent = Intent(context, DeleteActivity::class.java)
            intent.putExtra("medicamento", medicamento)
            activity.startActivityForResult(intent, 1)
        }*/
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //val mainLayout = itemView.findViewById<LinearLayout>(R.id.main_layout)
        val concept: TextView = itemView.findViewById(R.id.concept)
        val description: TextView = itemView.findViewById(R.id.description)
        val price: TextView = itemView.findViewById(R.id.price)
        val kindOfMove: TextView = itemView.findViewById(R.id.kind_of_move)
    }
}