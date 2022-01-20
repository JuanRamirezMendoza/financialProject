package com.financialproject.financialproject.ui.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import com.financialproject.financialproject.R
import com.financialproject.financialproject.databinding.FragmentInOutBinding

class FragmentInOut : Fragment() {

    private var _binding: FragmentInOutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentInOutBinding.inflate(inflater, container, false)

        val itemsKindMove = listOf("Incoming", "Expenses", "Safe")
        val adapterKindMove = ArrayAdapter(requireContext(), R.layout.dropdown_item, itemsKindMove)
        (binding.kindEdt as? AutoCompleteTextView)?.setAdapter(adapterKindMove)

        val itemsDescription = listOf("Transport", "Food", "Clothes","Market","Other Stuff")
        val adapterDescription = ArrayAdapter(requireContext(), R.layout.dropdown_item_description, itemsDescription)
        (binding.descriptionEdt as? AutoCompleteTextView)?.setAdapter(adapterDescription)

        return binding.root

    }
}