package com.example.les1mth4.ui.fragment.note

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.les1mth4.App
import com.example.les1mth4.data.model.NoteModel
import com.example.les1mth4.databinding.ItemNoteBinding
import com.example.les1mth4.utils.loadImage


class NoteAdapter : Adapter<NoteAdapter.NoteViewHolder>() {
    private var list : ArrayList<NoteModel> = arrayListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: ArrayList<NoteModel>) {
        this.list = list
        notifyDataSetChanged()
    }

    fun deleteItem(position: Int)  {
        App.dataBase.getDao().deleteNote(list.removeAt(position))
        notifyItemRemoved(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = NoteViewHolder(
        ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.onBind(list[position])
    }


    class NoteViewHolder(private val binding: ItemNoteBinding) : ViewHolder(binding.root) {

        fun onBind(model: NoteModel) {
            model.image.let { it?.let { it1 -> binding.imageNote.loadImage(it1) } }
            binding.titleNote.text = model.title
            binding.descNote.text = model.description
            binding.dateNote.text = model.date


        }
    }
}
