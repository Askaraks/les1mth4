package com.example.les1mth4.ui.fragment.note

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.les1mth4.data.model.NoteModel
import com.example.les1mth4.databinding.ItemNoteBinding

class AdapterNote(private val listener: SelectedListener) : RecyclerView.Adapter<AdapterNote.ViewHolder>() {
    private var list = arrayListOf<NoteModel>()

    fun setList(list: ArrayList<NoteModel>){
        this.list = list
        notifyDataSetChanged()
    }
    fun addNote(list:){
        this.list.add(list)
    }


    open inner class ViewHolder(val binding: ItemNoteBinding): this.ViewHolder(binding.root) {
        fun bind(noteModel: NoteModel) {
            binding.dateNote.text = noteModel.data
            binding.descNote.text = noteModel.description
            binding.titleNote.text = noteModel.title
            binding.imageNote.setImageURI(noteModel.image)

        }

    }

    override
    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemNoteBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
        holder.binding.imageNote.setOnLongClickListener{
            holder.binding.imageNote.alpha=0.5f
            listener.select(list[position])
            true
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
    interface SelectedListener{
        fun select(selectImg: NoteModel)
    }
}