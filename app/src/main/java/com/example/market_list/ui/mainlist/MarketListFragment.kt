package com.example.market_list.ui.mainlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import com.example.market_list.databinding.FragmentMarketListBinding

class MarketListFragment : Fragment() {
    private val viewModel: MarketListViewModel by viewModels {
        MarketListViewModel.Factory()
    }
    private lateinit var binding: FragmentMarketListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMarketListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListener()
        setupObserveStates()
    }

    private fun setupObserveStates() {
        TODO("Not yet implemented")
    }

    private fun setupListener() {
        setFragmentResultListener(MarketListMainDialog.FRAGMENT_RESULT) { requestKey, bundle ->
            val name = bundle.getString(MarketListMainDialog.EDIT_TEXT_VALUE) ?: ""
            viewModel.insertList(name)

            binding.fabAddList.setOnClickListener {
                handleShowDialog()
            }
        }
    }

    private fun handleShowDialog() {
        MarketListMainDialog.show(parentFragmentManager)
    }
}