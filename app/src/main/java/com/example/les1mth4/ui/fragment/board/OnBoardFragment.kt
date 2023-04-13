package com.example.les1mth4.ui.fragment.board

import androidx.navigation.fragment.findNavController
import com.example.les1mth4.R
import com.example.les1mth4.base.BaseFragment
import com.example.les1mth4.databinding.FragmentOnBoardPageBinding


class OnBoardFragment : BaseFragment<FragmentOnBoardPageBinding>(FragmentOnBoardPageBinding::inflate),
    BoardAdapter.ClickListener {
    private lateinit var adapter: BoardAdapter

    override fun setupUI() {
        adapter = BoardAdapter(this)
        binding.boardPager.adapter = adapter
        binding.springDotsIndicator.setViewPager2(binding.boardPager)

    }


    override fun click() {
        findNavController().navigate(R.id.noteFragment)

    }

    override fun NextCLicked() {
        binding.boardPager.setCurrentItem(++binding.boardPager.currentItem, true)
    }

    override fun SkipClicked() {
        binding.boardPager.setCurrentItem(binding.boardPager.adapter?.itemCount ?: 0, true)
    }
}

