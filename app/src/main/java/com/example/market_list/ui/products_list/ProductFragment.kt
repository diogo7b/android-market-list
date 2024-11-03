package com.example.market_list.ui.products_list

import android.annotation.SuppressLint
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
import com.example.market_list.domain.model.FullListDomain
import com.example.market_list.domain.model.ProductDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ProductFragment : Fragment() {

    private var idProductInUpdate: Int = 0
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
        setupAdapater()
        setupListener()
        setupObserveState()
        viewModel.getDetails(args.id)
    }

    @SuppressLint("DefaultLocale")
    private fun setupObserveState() {
        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                ProductState.Loading -> showLoadingState()
                ProductState.Empty -> showEmptyState()
                is ProductState.Error -> showErrorState()
                is ProductState.Success -> showSuccessState(it.list)
            }
        }
    }

    // Setups
    private fun setupViewer() {
        binding.mtDetailList.title = args.listName
    }

    private fun setupAdapater() {
        binding.rcDetailList.adapter = adapter
    }

    private fun setupListener() {
        setFragmentResultListener(ProductDialog.FRAGMENT_RESULT_CREATE) { _, bundle ->
            val name = bundle.getString(ProductDialog.NAME_ITEM_VALUE) ?: ""
            val price = bundle.getString(ProductDialog.UNIT_PRICE_VALUE) ?: ""
            val amount = bundle.getString(ProductDialog.AMOUNT_VALUE) ?: ""
            viewModel.insertProduct(name, price, amount, args.id)
        }

        setFragmentResultListener(UpdateProductDialog.FRAGMENT_RESULT_UPDATE) { _, bundle ->
            val name = bundle.getString(ProductDialog.NAME_ITEM_VALUE) ?: ""
            val price = bundle.getString(ProductDialog.UNIT_PRICE_VALUE) ?: ""
            val amount = bundle.getString(ProductDialog.AMOUNT_VALUE) ?: ""
            viewModel.updateProduct(
                idProductInUpdate, name, price.toDouble(), amount.toDouble(), args.id
            )
        }

        binding.fabAddItemDetail.setOnClickListener {
            handleCreateProductDialog()
        }

        adapter.apply {
            update = { product -> handleUpdateProductDialog(product) }

            delete = { product -> viewModel.deleteProduct(product) }
        }

        binding.mtDetailList.setNavigationOnClickListener {
            handleBackScreen()
        }
    }

    // Navigation and Dialog
    private fun handleBackScreen() {
        val action = ProductFragmentDirections.goToMarketListFragment()
        findNavController().navigate(action)
    }

    private fun handleUpdateProductDialog(item: ProductDomain) {
        idProductInUpdate = item.id
        UpdateProductDialog.show(item, parentFragmentManager)
    }

    private fun handleCreateProductDialog() {
        ProductDialog.show(parentFragmentManager)
    }

    // States
    private fun showEmptyState() = with(binding) {
        rcDetailList.isVisible = false
        tvTitleEmptyList.isVisible = true
        tvValorTotalValue.isVisible = false
        tvTitleEmptyList.text = getString(R.string.no_products)
        pbLoading.isVisible = false
    }

    private fun showErrorState() = with(binding) {
        rcDetailList.isVisible = false
        tvTitleEmptyList.isVisible = true
        tvTitleEmptyList.text = getString(R.string.error_message)
        tvValorTotalValue.isVisible = false
    }

    private fun showLoadingState() = with(binding) {
        pbLoading.isVisible = true
        rcDetailList.isVisible = false
        tvTitleEmptyList.isVisible = false
        tvValorTotalValue.isVisible = false
    }

    @SuppressLint("DefaultLocale")
    private fun showSuccessState(list: FullListDomain) = with(binding) {
        pbLoading.isVisible = false
        rcDetailList.isVisible = true
        tvTitleEmptyList.isVisible = false
        tvValorTotalValue.isVisible = true
        val totalListPriceFormatted = String.format(
            "%.2f", calcTotal(list.products)
        )
        tvValorTotalValue.text = getString(R.string.currency, totalListPriceFormatted)
        adapter.submitList(list.products)
    }

    private fun <T> Flow<T>.observe(owner: LifecycleOwner, observe: (T) -> Unit) {
        owner.lifecycleScope.launch {
            this@observe.collect(observe)
        }
    }

    private fun calcTotal(products: List<ProductDomain>) = products.sumOf { it.price }
}

