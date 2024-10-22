package com.example.market_list.ui.market_list

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.setFragmentResult
import com.example.market_list.databinding.MarketListDialogBinding
import com.example.market_list.domain.model.MarketListDomain

class UpdateMarketListDialog : DialogFragment() {

    private lateinit var binding: MarketListDialogBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return activity?.let {
            val titleList = arguments?.getString(EDIT_TEXT_VALUE) ?: "Campo vazio"
            val listId = arguments?.getString(ID_LIST) ?: "Campo vazio"

            binding = MarketListDialogBinding.inflate(requireActivity().layoutInflater).apply {
                etTitleList.setText(titleList)
            }

            AlertDialog.Builder(it)
                .setView(binding.root)
                .setPositiveButton("Confirmar") { _, _ ->
                    if (binding.etTitleList.text.toString().isBlank()) {
                        dismiss()
                    } else {
                        setFragmentResult(
                            FRAGMENT_RESULT,
                            bundleOf(
                                EDIT_TEXT_VALUE to binding.etTitleList.text.toString(),
                                ID_LIST to listId
                            )
                        )
                    }
                }
                .setNegativeButton("Cancelar") { _, _ -> dismiss() }
                .create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    companion object {

        const val FRAGMENT_RESULT = "FRAGMENT_RESULT"
        const val EDIT_TEXT_VALUE = "EDIT_TEXT_VALUE"
        const val ID_LIST = "ID_LIST"

        fun show(
            list: MarketListDomain,
            fragmentManager: FragmentManager,
            tag: String = UpdateMarketListDialog::class.simpleName.toString()
        ) {
            UpdateMarketListDialog().apply {
                arguments = bundleOf(
                    EDIT_TEXT_VALUE to list.listName,
                    ID_LIST to list.id.toString()
                )
            }.show(fragmentManager, tag)
        }
    }
}
