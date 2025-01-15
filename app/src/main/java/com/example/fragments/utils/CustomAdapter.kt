package com.example.fragments.utils

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fragments.model.Notes
import com.example.fragments.R

class CustomAdapter(private val notes: MutableList<Notes>, private val onItemClicked:(String)->Unit):
    RecyclerView.Adapter<CustomAdapter.NoteViewHolder>() {

    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val idTV: TextView = itemView.findViewById(R.id.idTV)
        val noteTV: TextView = itemView.findViewById(R.id.textNoteTV)
        val dateTV: TextView = itemView.findViewById(R.id.dateTV)
        val timeTV: TextView = itemView.findViewById(R.id.timeTV)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val noteView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return NoteViewHolder(noteView)
    }
    override fun getItemCount(): Int = notes.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.idTV.text = note.id.toString()
        holder.noteTV.text = note.noteText
        holder.dateTV.text = note.date
        holder.timeTV.text = note.time
        holder.itemView.setOnClickListener {
           onItemClicked(note.noteText.toString())
        }
    }
}
