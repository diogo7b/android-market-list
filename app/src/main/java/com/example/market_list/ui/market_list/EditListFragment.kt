package com.example.market_list.ui.market_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.market_list.databinding.EditListFragmentBinding

class EditListFragment : Fragment() {

    private lateinit var binding: EditListFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = EditListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

}