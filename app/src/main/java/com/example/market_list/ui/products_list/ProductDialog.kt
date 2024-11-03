package com.example.market_list.ui.products_list

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.setFragmentResult
import com.example.market_list.databinding.ProductDialogBinding

class ProductDialog : DialogFragment() {

    private lateinit var binding: ProductDialogBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return activity?.let {
            binding = ProductDialogBinding.inflate(requireActivity().layoutInflater).apply {
                tiNameItem.requestFocus()
            }

            AlertDialog.Builder(it)
                .setView(binding.root)
                .setPositiveButton("Confirmar") { _, _ ->
                    val name = binding.tiNameItem.editText?.text.toString()
                    var unitPrice = binding.tiUnitPrice.editText?.text.toString()
                    var amount = binding.tiAmount.editText?.text.toString()

                    if (name.isBlank()) {
                        Toast.makeText(
                            requireContext(),
                            "Preencha o nome do produto",
                            Toast.LENGTH_LONG
                        ).show()
                        show(parentFragmentManager)
                    } else {
                        unitPrice = unitPrice.ifBlank { "0.0" }
                        amount = amount.ifBlank { "0.0" }

                        setFragmentResult(
                            FRAGMENT_RESULT_CREATE,
                            bundleOf(
                                NAME_ITEM_VALUE to name,
                                UNIT_PRICE_VALUE to unitPrice,
                                AMOUNT_VALUE to amount
                            )
                        )
                    }
                }
                .setNegativeButton("Cancelar") { _, _ ->
                    dismiss()
                }.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    companion object {

        const val FRAGMENT_RESULT_CREATE = "FRAGMENT_RESULT_CREATE"
        const val NAME_ITEM_VALUE = "NAME_ITEM_VALUE"
        const val UNIT_PRICE_VALUE = "UNIT_PRICE_VALUE"
        const val AMOUNT_VALUE = "AMOUNT_VALUE"

        fun show(
            fragmentManager: FragmentManager,
            tag: String = ProductDialog::class.simpleName.toString()
        ) {
            ProductDialog().show(fragmentManager, tag)
        }
    }

}