package com.example.epolicemanagementsystem.report_screen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.epolicemanagementsystem.R
import kotlinx.android.synthetic.main.activity_reportmain.view.*
import kotlinx.android.synthetic.main.rv_layout.view.*

class DataAdapter (var list: ArrayList<DatabaseModel>): RecyclerView.Adapter<DataAdapter.ViewHolder> () {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var idNo = itemView.tv_name
        var name_surname = itemView.tv_email
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.rv_layout,parent,false))
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.idNo.text = list[position].idNo
        holder.name_surname.text = list[position].name_surname

    }

    override fun getItemCount(): Int {
        return list.size
    }


}