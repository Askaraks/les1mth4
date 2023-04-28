package com.example.les1mth4.ui.fragment.contact

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.les1mth4.data.model.ContactModel
import com.example.les1mth4.databinding.ItemContactBinding

class ContactAdapter(
    private val listener: ShareContactListener
) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {
    private var list = listOf<ContactModel>()

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<ContactModel>){
        this.list = list
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        ItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    }


    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.onBind(list[position)
    }

    inner class ContactViewHolder(private val binding: ItemContactBinding)
        : ViewHolder(binding.root) {
            fun onBind(model: ContactModel){
                binding.itemContactName.text = model.name
                binding.itemContactPhone.text = model.phone

                binding.itemBtnCall.setOnClickListener {
                    model.phone?.let { it1 -> listener.shareCall(it1) }
                }
                binding.itemBtnChat.setOnClickListener { model.phone?.let { it1 ->
                    listener.shareWhatsApp(
                        it1
                    )
                } }
            }
    }
    interface ShareContactListener {
        fun shareCall(number: String)
        fun shareWhatsApp(number: String)
    }
}