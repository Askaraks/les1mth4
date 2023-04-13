package com.example.les1mth4.ui.fragment.note

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.les1mth4.R
import com.example.les1mth4.data.model.NoteModel

class NotesAdapter(private val listener: OnNoteClickListener) :
    RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    interface OnNoteClickListener {
        fun onNoteClick(note: NoteModel)
    }

    private val notes = mutableListOf<NoteModel>()

    fun setNotesList(notesList: List<NoteModel>) {
        notes.clear()
        notes.addAll(notesList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(itemView)
    }

    override fun getItemCount(): Int = notes.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.bind(note)
        holder.itemView.setOnClickListener { listener.onNoteClick(note) }
    }

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(note: NoteModel) {
            val titleTextView: TextView = itemView.findViewById(R.id.title)
            val dateTextView: TextView = itemView.findViewById(R.id.description)

            titleTextView.text = note.title
            dateTextView.text = note.date.toString()
        }
    }
}