package com.example.market_list.ui.market_list

import android.os.Bundle
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

    //States
    private fun showSuccessState(marketLists: List<MarketListDomain>) = with(binding) {
        pbLoading.isVisible = false
        tvTitleEmptyList.isVisible = false
        rcMarketLists.isVisible = true
        adapter.submitList(marketLists)
    }

    private fun showEmptyState() = with(binding) {
        pbLoading.isVisible = false
        rcMarketLists.isVisible = false
        tvTitleEmptyList.isVisible = true
        Toast.makeText(
            requireContext(),
            "Sem receitas no momento",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun showErrorState() = with(binding) {
        pbLoading.isVisible = false
        rcMarketLists.isVisible = false
        tvTitleEmptyList.isVisible = true
        tvTitleEmptyList.text = getString(R.string.error_message)
    }

    private fun showLoadingState() = with(binding) {
        pbLoading.isVisible = true
        rcMarketLists.isVisible = false
        tvTitleEmptyList.isVisible = false
    }

    //Setup Functions
    private fun setupAdapater() {
        binding.rcMarketLists.adapter = adapter
    }

    private fun setupListener() {
        setFragmentResultListener(MarketListMainDialog.FRAGMENT_RESULT_CREATE) { _, bundle ->
            val name = bundle.getString(MarketListMainDialog.EDIT_TEXT_VALUE) ?: ""
            viewModel.insertList(name)
        }

        setFragmentResultListener(UpdateMarketListDialog.FRAGMENT_RESULT_UPDATE) { _, bundle ->
            val idList: String = bundle.getString(UpdateMarketListDialog.ID_LIST) ?: ""
            val name = bundle.getString(UpdateMarketListDialog.EDIT_TEXT_VALUE) ?: ""
            viewModel.updateList(idList.toInt(), name)
        }

        binding.fabAddList.setOnClickListener {
            showDialogCreateList()
        }

        adapter.apply {
            click = { list -> navigateToProductFragment(list) }
            update = { list -> showDialogUpdateList(list) }
            delete = { list -> viewModel.deleteList(list) }
        }
    }

    private fun setupObserveState() {
        viewModel.state.observe(owner = viewLifecycleOwner) { state ->
            when (state) {
                MarketListState.Empty -> showEmptyState()
                is MarketListState.Error -> showErrorState()
                MarketListState.Loading -> showLoadingState()
                is MarketListState.Success -> showSuccessState(state.marketList)
            }
        }
    }

    //Navigation and Dialogs
    private fun navigateToProductFragment(list: MarketListDomain) {
        val action =
            MarketListFragmentDirections.goToProductFragment(list.id, list.listName)
        findNavController().navigate(action)
    }

    private fun showDialogUpdateList(list: MarketListDomain) {
        UpdateMarketListDialog.show(list, parentFragmentManager)
    }

    private fun showDialogCreateList() {
        MarketListMainDialog.show(parentFragmentManager)
    }

    fun <T> Flow<T>.observe(owner: LifecycleOwner, observe: (T) -> Unit) {
        owner.lifecycleScope.launch {
            this@observe.collect(observe)
        }
    }

}