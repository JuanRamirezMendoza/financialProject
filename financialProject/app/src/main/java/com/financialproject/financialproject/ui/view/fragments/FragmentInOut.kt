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

        val types = resources.getStringArray(R.array.types)
        println(
            "XD<"
                    + types.size
        )
        val items = listOf("Incoming", "Expenses", "Safe")
        val adapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, items)
        (binding.kindEdt as? AutoCompleteTextView)?.setAdapter(adapter)

        return binding.root

    }
}