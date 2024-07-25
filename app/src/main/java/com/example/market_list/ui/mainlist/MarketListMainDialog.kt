package com.example.market_list.ui.mainlist

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.setFragmentResult
import com.example.market_list.databinding.MarketListDialogBinding

class MarketListMainDialog : DialogFragment() {

    private lateinit var binding: MarketListDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return activity?.let {
            binding =
                MarketListDialogBinding.inflate(requireActivity().layoutInflater)

            //para utilizar o set fragment result precisa importar a dependencia androidx.fragment:fragment-ktx
            AlertDialog.Builder(it)
                .setView(binding.root)
                .setPositiveButton("Confirmar") { _, _ ->
                    setFragmentResult(
                        FRAGMENT_RESULT, bundleOf(
                            EDIT_TEXT_VALUE to binding.etTitleList.text.toString()
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
        const val EDIT_TEXT_VALUE = "EDIT_TEXT_VALUE"

        fun show(
            fragmentManager: FragmentManager,
            tag: String = MarketListMainDialog::class.simpleName.toString()
        ) {
            MarketListMainDialog().show(fragmentManager, tag)
        }
    }
}


