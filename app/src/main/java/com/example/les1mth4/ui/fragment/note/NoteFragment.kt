package com.example.les1mth4.ui.fragment.note


import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.SimpleCallback
import androidx.recyclerview.widget.RecyclerView
import com.example.les1mth4.App
import com.example.les1mth4.R
import com.example.les1mth4.base.BaseFragment
import com.example.les1mth4.data.model.NoteModel
import com.example.les1mth4.databinding.FragmentNoteBinding

class NoteFragment : BaseFragment<FragmentNoteBinding>(FragmentNoteBinding::inflate) {
    private val adapter: NoteAdapter by lazy { NoteAdapter() }

    override fun setupUI() {
        binding.recycler.adapter = adapter

    }

    override fun setupObserver() {
        deleteNote()
        binding.btnFloat.setOnClickListener {
            findNavController().navigate(R.id.addNoteFragment)
        }
        adapter.setList(App.dataBase.getDao().getAllNote() as ArrayList<NoteModel>)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun deleteNote() {
        val simpleCallback =
            object : SimpleCallback(0, ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    AlertDialog.Builder(requireContext())
                        .setTitle("Вы уверены удалить это?")
                        .setNegativeButton("Нет") { _: DialogInterface, _: Int ->
                            adapter.notifyItemChanged(viewHolder.adapterPosition)
                        }
                        .setPositiveButton("Да") { _: DialogInterface, _: Int ->
                            adapter.deleteItem(viewHolder.adapterPosition)
                        }.show()
                }
            }

        val itemTouchHelper = ItemTouchHelper(simpleCallback)
        itemTouchHelper.attachToRecyclerView(binding.recycler)


    }
}