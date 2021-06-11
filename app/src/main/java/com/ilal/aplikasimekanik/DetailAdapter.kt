package com.ilal.aplikasimekanik

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ilal.aplikasimekanik.database.detail.Detail
import kotlinx.android.synthetic.main.detail_view.view.*

class DetailAdapter(private val context: Context?, private val listener: (Detail, Int) -> Unit) :
    RecyclerView.Adapter<DetailViewHolder>() {

    private var details = listOf<Detail>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        return DetailViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.detail_view, parent, false
            )
        )
    }

    override fun getItemCount(): Int = details.size

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        if (context != null){
            holder.bindItem(context, details[position], listener)
        }
    }

    fun setDetails(details: List<Detail>){
        this.details = details
        notifyDataSetChanged()
    }

}

class DetailViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
    fun bindItem(context: Context, detail: Detail, listener: (Detail, Int) -> Unit) {
        itemView.tvNama.text = detail.namaMekanik
        itemView.tvTipe.text = detail.masalahMotor
        itemView.tvKondisi.text = detail.solusiMotor
        itemView.setOnClickListener{
            listener(detail, layoutPosition)
        }
    }
}