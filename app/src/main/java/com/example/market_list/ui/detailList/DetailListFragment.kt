package com.example.market_list.ui.detailList

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.market_list.databinding.FragmentDetailListBinding

class DetailListFragment : Fragment() {
    private lateinit var binding: FragmentDetailListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailListBinding.inflate(inflater, container, false)
        arguments?.let{
            val id = it.getString("idList")
            val listName = it.getString("nameList")
            Log.d("teste_id", id.toString())
            Log.d("teste_name", listName.toString())
        }
        return binding.root
    }
}