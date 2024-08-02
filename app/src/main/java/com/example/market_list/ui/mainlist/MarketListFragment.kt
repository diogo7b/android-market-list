package com.example.market_list.ui.mainlist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.example.market_list.databinding.FragmentMarketListBinding
import com.example.market_list.domain.model.MarketListDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MarketListFragment : Fragment() {

    private val viewModel: MarketListViewModel by viewModels {
        MarketListViewModel.Factory()
    }
    private lateinit var binding: FragmentMarketListBinding
    private val adapter by lazy { MarketListAdapter() }

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
        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                MarketListState.Empty -> {
                    emptyState()

                }

                is MarketListState.Error -> {
                    errorState()

                }

                MarketListState.Loading -> {
                    loadingState()

                }

                is MarketListState.Success -> {
                    successState(it.marketList)


                }
            }
        }
    }

    private fun successState(marketLists: List<MarketListDomain>) {
        binding.pbLoading.isVisible = false
        binding.rcMarketLists.isVisible = true
        binding.rcMarketLists.adapter = adapter
        adapter.submitList(marketLists)
    }

    private fun emptyState() {
        binding.pbLoading.isVisible = false
        binding.rcMarketLists.isVisible = false
        binding.tvTitleEmptyList.isVisible = true
        Toast.makeText(
            requireContext(),
            "Sem receitas no momento",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun errorState() {
        binding.pbLoading.isVisible = false
        binding.rcMarketLists.isVisible = false
        binding.tvTitleEmptyList.isVisible = true
        binding.tvTitleEmptyList.text = "Ocorreu um erro"
    }

    private fun loadingState() {
        binding.pbLoading.isVisible = true
        binding.rcMarketLists.isVisible = false
        binding.tvTitleEmptyList.isVisible = false
    }

    private fun setupListener() {
        setFragmentResultListener(MarketListMainDialog.FRAGMENT_RESULT) { requestKey, bundle ->
            val name = bundle.getString(MarketListMainDialog.EDIT_TEXT_VALUE) ?: ""
            viewModel.insertList(name)
        }

        binding.fabAddList.setOnClickListener {
            handleShowDialog()
        }
    }

    private fun handleShowDialog() {
        MarketListMainDialog.show(parentFragmentManager)
    }

    fun <T> Flow<T>.observe(owner: LifecycleOwner, observe: (T) -> Unit) {
        owner.lifecycleScope.launch {
            this@observe.collect(observe)
        }
    }
}