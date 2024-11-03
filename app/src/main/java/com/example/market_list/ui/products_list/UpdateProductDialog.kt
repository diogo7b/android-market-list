package com.example.market_list.ui.products_list

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.setFragmentResult
import com.example.market_list.R
import com.example.market_list.databinding.ProductDialogBinding
import com.example.market_list.domain.model.ProductDomain

class UpdateProductDialog : DialogFragment() {

    private lateinit var binding: ProductDialogBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return activity?.let {
            val name = arguments?.getString(NAME_ITEM_VALUE) ?: "Campo vazio"

            val price = arguments?.getString(UNIT_PRICE_VALUE) ?: "Campo vazio"

            val amount = arguments?.getString(AMOUNT_VALUE) ?: "Campo vazio"
            binding = ProductDialogBinding.inflate(requireActivity().layoutInflater).apply {
                tvDialogTitle.setText(getString(R.string.edit_product))
                tiNameItem.editText?.setText(name)
                tiUnitPrice.editText?.setText(price)
                tiAmount.editText?.setText(amount)
            }
            AlertDialog.Builder(it)
                .setView(binding.root)
                .setPositiveButton("Confirmar") { _, _ ->
                    setFragmentResult(
                        FRAGMENT_RESULT_UPDATE,
                        bundleOf(
                            NAME_ITEM_VALUE to binding.tiNameItem.editText?.text.toString(),
                            UNIT_PRICE_VALUE to binding.tiUnitPrice.editText?.text.toString(),
                            AMOUNT_VALUE to binding.tiAmount.editText?.text.toString()
                        )
                    )
                }
                .setNegativeButton("Cancelar") { _, _ ->
                    dismiss()
                }.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    companion object {

        const val FRAGMENT_RESULT_UPDATE = "FRAGMENT_RESULT_UPDATE"
        const val NAME_ITEM_VALUE = "NAME_ITEM_VALUE"
        const val UNIT_PRICE_VALUE = "UNIT_PRICE_VALUE"
        const val AMOUNT_VALUE = "AMOUNT_VALUE"

        fun show(
            product: ProductDomain,
            fragmentManager: FragmentManager,
            tag: String = UpdateProductDialog::class.simpleName.toString()
        ) {
            UpdateProductDialog().apply {
                arguments = bundleOf(
                    NAME_ITEM_VALUE to product.name,
                    UNIT_PRICE_VALUE to product.price.toString(),
                    AMOUNT_VALUE to product.amount.toString()
                )
            }.show(fragmentManager, tag)
        }
    }
}
