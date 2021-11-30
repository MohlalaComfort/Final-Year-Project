package com.example.administrator.generateReport

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.administrator.R
import kotlinx.android.synthetic.main.activity_reportmain.view.*
import kotlinx.android.synthetic.main.rv_layout.view.*


class DataAdapter (var list: ArrayList<DatabaseModel>): RecyclerView.Adapter<DataAdapter.ViewHolder> (){
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        var idNo =itemView.tv_name
        var name_surname = itemView.tv_email
        var contacts = itemView.tv_contacts
        var description = itemView.tv_desc

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.rv_layout,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.idNo.text = list[position].idNo
        holder.name_surname.text = list[position].name_surname
        holder.contacts.text = list[position].contacts
        holder.description.text = list[position].description
    }
    override fun getItemCount(): Int {
        return list.size
    }


}

