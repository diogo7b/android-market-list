package com.example.market_list.ui.detailList

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.market_list.databinding.FragmentDetailListBinding

class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailListBinding
    private val adapter by lazy { DetailsAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailListBinding.inflate(inflater, container, false)
        val list_info = arguments?.getString("list_info")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}