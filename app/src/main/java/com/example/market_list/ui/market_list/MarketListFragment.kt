package com.example.market_list.ui.market_list

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
import androidx.navigation.fragment.findNavController
import com.example.market_list.R
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

        setupAdapater()
        setupListener()
        setupObserveState()
    }

    private fun setupObserveState() {
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
        binding.tvTitleEmptyList.isVisible = false
        binding.rcMarketLists.isVisible = true
        adapter.submitList(marketLists)
    }

    private fun setupAdapater() {
        binding.rcMarketLists.adapter = adapter
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
        binding.tvTitleEmptyList.text = getString(R.string.error_message)
    }

    private fun loadingState() {
        binding.pbLoading.isVisible = true
        binding.rcMarketLists.isVisible = false
        binding.tvTitleEmptyList.isVisible = false
    }

    private fun setupListener() {
        setFragmentResultListener(MarketListMainDialog.FRAGMENT_RESULT) { _, bundle ->
            val name = bundle.getString(MarketListMainDialog.EDIT_TEXT_VALUE) ?: ""
            viewModel.insertList(name)
        }

        setFragmentResultListener(UpdateMarketListDialog.FRAGMENT_RESULT) { _, bundle ->
            val name = bundle.getString(MarketListMainDialog.EDIT_TEXT_VALUE) ?: ""
            viewModel.updateList(name)
        }

        binding.fabAddList.setOnClickListener {
            handleShowDialog()
        }
        adapter.click = { list ->
            val action = MarketListFragmentDirections.goToProductFragment(list.id, list.listName)
            findNavController().navigate(action)
        }
        adapter.update = { list ->
            Log.d("teste_update", "update: $list")
        }
        adapter.delete = { list ->
            viewModel.deleteList(list)
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