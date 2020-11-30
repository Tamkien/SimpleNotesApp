package com.kienct.simplenotesapp.entities

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.kienct.simplenotesapp.R
import com.kienct.simplenotesapp.activities.UpdateActivity
import kotlinx.android.synthetic.main.todo_item_layout.view.*

class ItemAdapter(private val list: List<Item>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.todo_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder) {
            holder.bind(list[position])
            holder.itemView.findViewById<ConstraintLayout>(R.id.itemLayout).setBackgroundResource(
                when (position % 3) {
                    0 -> R.drawable.bg_red
                    1 -> R.drawable.bg_blue
                    else -> R.drawable.bg_green
                }
            )
            holder.itemView.setOnClickListener { v ->
                val intent = Intent(v.context, UpdateActivity::class.java)
                intent.putExtra("Title", holder.itemView.tvTitle.text)
                intent.putExtra("Content", holder.itemView.tvContent.text)
                intent.putExtra("ID", holder.itemView.itemID.text)
                intent.putExtra("IsFavorite", holder.itemView.ratingBar.rating.toInt())
                v.context.startActivity(intent)
            }
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Item) {
            val title: TextView = itemView.findViewById(R.id.tvTitle)
            val content: TextView = itemView.findViewById(R.id.tvLastEdited)
            val lastEdited: TextView = itemView.findViewById(R.id.tvContent)
            val id: TextView = itemView.findViewById(R.id.itemID)
            val isFavorite: RatingBar = itemView.findViewById(R.id.ratingBar)
            title.text = item.title
            content.text = item.content
            lastEdited.text = item.lastEdited
            id.text = item.id.toString()
            isFavorite.rating = item.isFavourite.toFloat()
            Log.d("Is fav", item.isFavourite.toString())
//            itemView.findViewById<ConstraintLayout>(R.id.itemLayout).setBackgroundResource(
//                when (position % 3) {
//                    0 -> R.drawable.bg_red
//                    1 -> R.drawable.bg_blue
//                    else -> R.drawable.bg_green
//                }
//            )
        }
    }
}