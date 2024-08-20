package com.example.market_list.ui.detailList

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.setFragmentResult
import com.example.market_list.databinding.DetailListDialogBinding


class DetailListDialog : DialogFragment() {
    private lateinit var binding: DetailListDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return activity?.let {
            binding = DetailListDialogBinding.inflate(requireActivity().layoutInflater)

            AlertDialog.Builder(it)
                .setView(binding.root)
                .setPositiveButton("Confirmar") { _, _ ->
                    setFragmentResult(
                        FRAGMENT_RESULT,
                        bundleOf(
                            NAME_ITEM_VALUE to binding.etNameItem.text.toString(),
                            UNIT_PRICE_VALUE to binding.etUnitPrice.text.toString(),
                            AMOUNT_VALUE to binding.etAmount.text.toString()
                        )
                    )
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
    }

    fun showDialog(
        fragmentManager: FragmentManager,
        tag: String = DetailListDialog::class.simpleName.toString()
    ) {
        DetailListDialog().show(fragmentManager, tag)
    }

}