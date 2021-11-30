package com.example.lista.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lista.R
import com.example.lista.model.Item

class ItemAdapter(
    private val context: Context,

    private val dados: MutableList<Item>
    ) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {


    var onItemClick: ((Item) -> Unit)? = null

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        val txtInfo1: TextView = view.findViewById(R.id.txtInfo1)
        val txtInfo2: TextView = view.findViewById(R.id.txtInfo2)
        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(dados[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.linha_recyclerview, parent, false)
        return ViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = dados.get(position)
        holder.txtInfo1.text = item.info1
        holder.txtInfo2.text = item.info2
    }
    override fun getItemCount(): Int {
        return dados.size
    }
}