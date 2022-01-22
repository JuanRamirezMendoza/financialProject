package com.financialproject.financialproject.ui.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.financialproject.financialproject.R
import com.financialproject.financialproject.data.extensionfunctions.toast
import com.financialproject.financialproject.databinding.FragmentInOutBinding
import com.financialproject.financialproject.ui.viewmodel.FragmenteInOutViewModel
import com.financialproject.financialproject.ui.viewmodel.SUCCESS

class FragmentInOut : Fragment() {

    private lateinit var fragmenteInOutViewModel: FragmenteInOutViewModel
    private var _binding: FragmentInOutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentInOutBinding.inflate(inflater, container, false)

        fragmenteInOutViewModel = ViewModelProvider(this)[FragmenteInOutViewModel::class.java]

        binding.viewModelFragmentInOut = fragmenteInOutViewModel
        binding.lifecycleOwner = this

        val itemsKindMove = listOf("Incoming", "Expenses", "Safe")
        val adapterKindMove = ArrayAdapter(requireContext(), R.layout.dropdown_item, itemsKindMove)
        (binding.kindEdt as? AutoCompleteTextView)?.setAdapter(adapterKindMove)

        val itemsDescription = listOf("Transport", "Food", "Clothes", "Market", "Other Stuff")
        val adapterDescription =
            ArrayAdapter(requireContext(), R.layout.dropdown_item_description, itemsDescription)
        (binding.descriptionEdt as? AutoCompleteTextView)?.setAdapter(adapterDescription)

        fragmenteInOutViewModel.success.observe(activity, {
                when (it) {
                    SUCCESS.LOG_OUT_SUCCESS -> {
                        toast("sesion cerrada correctamente")
                    }
                }
            }
        )
        return binding.root

    }
}