package com.example.les1mth4.ui.fragment.note

import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.les1mth4.R
import com.example.les1mth4.base.BaseFragment
import com.example.les1mth4.data.model.NoteModel
import com.example.les1mth4.databinding.FragmentNoteBinding


class NoteFragment : BaseFragment<FragmentNoteBinding>(FragmentNoteBinding::inflate), NotesAdapter.OnNoteClickListener {
    private lateinit var adapter: NotesAdapter
    override fun setupUI() {
        adapter = NotesAdapter(this)
        binding.recycler.adapter = adapter

        binding.btnFloat.setOnClickListener{
            findNavController().navigate(R.id.addNoteFragment)
    }
}


    override fun setupObserver() {

    }

    override fun onNoteClick(note: NoteModel) {
        TODO("Not yet implemented")
    }
}
