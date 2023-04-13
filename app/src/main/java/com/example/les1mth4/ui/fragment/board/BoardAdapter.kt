package com.example.les1mth4.ui.fragment.board

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.les1mth4.R
import com.example.les1mth4.databinding.FragmentOnBoardPageBinding


class BoardAdapter(private val listener: ClickListener): Adapter<BoardAdapter.BoardViewHolder>() {


    private val listImg =
        arrayListOf(R.drawable.ic_onboarding1, R.drawable.ic_onboarding2, R.drawable.ic_onboarding3)

    private val listTitle =
        arrayListOf("To-do list!", "Share your crazy idea ^_^", "Flexibility")

    private val listDes =
        arrayListOf("Here you can write down something important or make a schedule for tomorrow:)",
            "You can easily share with your report, list or schedule and it's convenient",
            "Your note with you at home, at work, even at the resort")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = BoardViewHolder(
        FragmentOnBoardPageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount() = listImg.size

    override fun onBindViewHolder(holder: BoardViewHolder, position: Int) {
        holder.onBind(position)
        holder.binding.btnStart.setOnClickListener {
            listener.click()
        }
        holder.binding.btnNext.setOnClickListener{
            listener.NextCLicked()
        }
        holder.binding.btnNext.setOnClickListener{
            listener.SkipClicked()
        }
    }

    inner class BoardViewHolder(val binding: FragmentOnBoardPageBinding) : ViewHolder(binding.root) {
        fun onBind(position: Int) {

            binding.ivBoardPicture.setImageResource(listImg[position])
            binding.tvTitle.text = listTitle[position]
            binding.tvDesc.text = listDes[position]

            if (position == listImg.size - 1) {
                binding.ivBoardPicture.isVisible = true
                binding.btnNext.isGone = true
                binding.tvSkip.isGone = true
            } else {
                binding.btnStart.isGone = true
            }
        }
    }

    interface ClickListener {
        fun click()
        fun NextCLicked()
        fun SkipClicked()
    }
}