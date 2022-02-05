package com.financialproject.financialproject.ui.view.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.financialproject.financialproject.R
import com.financialproject.financialproject.data.extensionfunctions.formatNumber
import com.financialproject.financialproject.data.extensionfunctions.toast
import com.financialproject.financialproject.databinding.FragmentInOutBinding
import com.financialproject.financialproject.ui.view.MenuView
import com.financialproject.financialproject.ui.viewmodel.ERROR
import com.financialproject.financialproject.ui.viewmodel.FragmenteInOutViewModel
import com.financialproject.financialproject.ui.viewmodel.NAVIGATION
import com.financialproject.financialproject.ui.viewmodel.SUCCESS
import java.text.DecimalFormat

class FragmentInOut : Fragment() {

    private lateinit var fragmenteInOutViewModel: FragmenteInOutViewModel
    private lateinit var binding: FragmentInOutBinding

    @SuppressLint("FragmentLiveDataObserve")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmenteInOutViewModel = ViewModelProvider(this)[FragmenteInOutViewModel::class.java]
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_in_out, container, false)
        binding.viewModelFragmentInOut = fragmenteInOutViewModel
        binding.lifecycleOwner = this

        val itemsKindMove = listOf("Incoming", "Expenses", "Safe")
        val adapterKindMove = ArrayAdapter(requireContext(), R.layout.dropdown_item, itemsKindMove)
        (binding.kindEdt as? AutoCompleteTextView)?.setAdapter(adapterKindMove)

        val itemsDescription = listOf("Transport", "Food", "Clothes", "Market", "Other Stuff")
        val adapterDescription =
            ArrayAdapter(requireContext(), R.layout.dropdown_item_description, itemsDescription)
        (binding.descriptionEdt as? AutoCompleteTextView)?.setAdapter(adapterDescription)

        binding.priceText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                var current = ""
                if (s.toString() != current) {
                    binding.priceText.removeTextChangedListener(this)

                    val cleanString: String = s?.replace(
                        """[$,.]""".toRegex(),
                        ""
                    ) ?: ""

                    current = "$"+ formatNumber(cleanString)
                    binding.priceText.setText(current)
                    binding.priceText.setSelection(current.length)

                    binding.priceText.addTextChangedListener(this)
                }
            }
        })

        fragmenteInOutViewModel.success.observe(this, {
            when (it) {
                SUCCESS.REGISTER_SUCCES -> {
                    toast("Register OK")
                }
            }
        })

        fragmenteInOutViewModel.error.observe(this, {
            when (it) {
                ERROR.EMPTY_FIELDS -> {
                    toast("Fill all fields")
                }
                ERROR.COULD_NOT_ADD -> {
                    toast("Could not add register")
                }
            }
        })

        fragmenteInOutViewModel.navigation.observe(this, {
            when (it) {
                NAVIGATION.GO_MAIN_VIEW -> {
                    val intent = Intent(context, MenuView::class.java)
                    context?.startActivity(intent)
                }
            }
        })
        return binding.root

    }

}