package com.example.market_list.ui.detailList

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.market_list.databinding.FragmentDetailListBinding
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class DetailsFragment : Fragment() {


    private val viewModel: DetailsViewModel by viewModels {
        DetailsViewModel.Factory()
    }
    private lateinit var binding: FragmentDetailListBinding
    private val adapter by lazy { DetailsAdapter() }
    private val args by navArgs<DetailsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }




}