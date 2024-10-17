package com.example.market_list.ui.market_list

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.setFragmentResult
import com.example.market_list.databinding.MarketListDialogBinding

class MarketListMainDialog : DialogFragment() {

    private lateinit var binding: MarketListDialogBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return activity?.let {
            binding = MarketListDialogBinding.inflate(requireActivity().layoutInflater)

            //para utilizar o set fragment result precisa importar a dependencia androidx.fragment:fragment-ktx
            AlertDialog.Builder(it).setView(binding.root).setPositiveButton("Confirmar") { _, _ ->
                    if (binding.etTitleList.text.toString().isNotBlank()) {
                        setFragmentResult(
                            FRAGMENT_RESULT, bundleOf(
                                EDIT_TEXT_VALUE to binding.etTitleList.text.toString()
                            )
                        )
                    } else {
                        dismiss()
                        Toast.makeText(requireContext(), "Digite um titulo", Toast.LENGTH_LONG)
                            .show()
                    }
                }.setNegativeButton("Cancelar") { _, _ ->
                    dismiss()
                }.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    companion object {

        const val FRAGMENT_RESULT = "FRAGMENT_RESULT"
        const val EDIT_TEXT_VALUE = "EDIT_TEXT_VALUE"

        fun show(
            fragmentManager: FragmentManager,
            tag: String = MarketListMainDialog::class.simpleName.toString()
        ) {
            MarketListMainDialog().show(fragmentManager, tag)
        }

    }
}


