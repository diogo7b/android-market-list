package com.example.market_list.ui.products_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.market_list.R
import com.example.market_list.databinding.FragmentProductListBinding
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ProductFragment : Fragment() {

    private val viewModel: ProductViewModel by viewModels {
        ProductViewModel.Factory()
    }
    private lateinit var binding: FragmentProductListBinding
    private val adapter by lazy { DetailsAdapter() }
    private val args by navArgs<ProductFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewer()
        setupListener()
        setupObserveState()
    }

    private fun setupObserveState() {
        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                ProductState.Loading -> {
                    loadingState()
                }

                ProductState.Empty -> {
                    emptyState()
                }

                is ProductState.Error -> {
                    errorState()
                }

                is ProductState.Success -> {
                    successState()
                }
            }
        }
    }

    private fun setupViewer() {
        binding.mtDetailList.title = args.listName
    }

    private fun setupListener() {
        setFragmentResultListener(ProductDialog.FRAGMENT_RESULT) { _, bundle ->
            val name = bundle.getString(ProductDialog.NAME_ITEM_VALUE) ?: ""
            val price = bundle.getString(ProductDialog.UNIT_PRICE_VALUE) ?: ""
            val amount = bundle.getString(ProductDialog.AMOUNT_VALUE) ?: ""
            viewModel.insertProduct(name, price, amount, args.id)

        }

        binding.fabAddItemDetail.setOnClickListener {
            handleShowDialog()
        }
        binding.mtDetailList.setNavigationOnClickListener {
            val action = ProductFragmentDirections.goToMarketListFragment()
            findNavController().navigate(action)
        }
    }

    private fun handleShowDialog() {
        ProductDialog.show(parentFragmentManager)
    }

    fun <T> Flow<T>.observe(owner: LifecycleOwner, observe: (T) -> Unit) {
        owner.lifecycleScope.launch {
            this@observe.collect(observe)
        }
    }

    private fun emptyState() {
        binding.rcDetailList.isVisible = false
        binding.tvTitleEmptyList.isVisible = true
        binding.tvValorTotalValue.isVisible = false
    }

    private fun errorState() {
        binding.rcDetailList.isVisible = false
        binding.tvTitleEmptyList.isVisible = true
        binding.tvTitleEmptyList.text = getString(R.string.error_message)
        binding.tvValorTotalValue.isVisible = false
    }

    private fun loadingState() {
        binding.pbLoading.isVisible = true
        binding.rcDetailList.isVisible = false
        binding.tvTitleEmptyList.isVisible = false
        binding.tvValorTotalValue.isVisible = false
    }

    private fun successState() {
        binding.pbLoading.isVisible = false
        binding.rcDetailList.isVisible = true
        binding.tvTitleEmptyList.isVisible = false
        binding.tvValorTotalValue.isVisible = true
    }
}

