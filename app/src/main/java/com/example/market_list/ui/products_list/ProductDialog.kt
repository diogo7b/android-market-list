package com.example.market_list.ui.products_list

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
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
            binding = ProductDialogBinding.inflate(requireActivity().layoutInflater)

            AlertDialog.Builder(it)
                .setView(binding.root)
                .setPositiveButton("Confirmar") { _, _ ->

                    if (
                        binding.etNameItem.text.toString().isEmpty() ||
                        binding.etUnitPrice.text.toString().isEmpty() ||
                        binding.etAmount.text.toString().isEmpty()
                    ) {
                        dismiss()
                        Toast.makeText(
                            requireContext(),
                            "Preencha todos os campos",
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        setFragmentResult(
                            FRAGMENT_RESULT,
                            bundleOf(
                                NAME_ITEM_VALUE to binding.etNameItem.text.toString(),
                                UNIT_PRICE_VALUE to binding.etUnitPrice.text.toString(),
                                AMOUNT_VALUE to binding.etAmount.text.toString()
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

        const val FRAGMENT_RESULT = "FRAGMENT_RESULT"
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