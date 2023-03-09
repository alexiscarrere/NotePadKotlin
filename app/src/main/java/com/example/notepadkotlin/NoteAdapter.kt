package com.example.notepadkotlin

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter(val notes: List<Note>, val itemClickListener: View.OnClickListener)
    : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val cardView = itemView.findViewById(R.id.card_view) as CardView
        val titleView = itemView.findViewById(R.id.title) as TextView
        val exerptView = itemView.findViewById(R.id.exerpt) as TextView

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewItem = LayoutInflater.from(parent.context).inflate(
            R.layout.item_note_list, parent, false)

        return ViewHolder(viewItem)

    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = notes[position]
        holder.cardView.setOnClickListener(itemClickListener)
        holder.cardView.tag = position
        holder.titleView.text = note.title
        holder.exerptView.text = note.text

    }


}