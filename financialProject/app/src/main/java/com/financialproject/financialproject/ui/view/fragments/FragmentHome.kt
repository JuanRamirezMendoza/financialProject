package com.financialproject.financialproject.ui.view.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.financialproject.financialproject.R
import com.financialproject.financialproject.data.extensionfunctions.toast
import com.financialproject.financialproject.data.model.InOut
import com.financialproject.financialproject.databinding.FragmentHomeBinding
import com.financialproject.financialproject.ui.view.LoginView
import com.financialproject.financialproject.ui.view.adapter.MenuAdapter
import com.financialproject.financialproject.ui.viewmodel.FragmentHomeViewModel
import com.financialproject.financialproject.ui.viewmodel.NAVIGATION
import com.financialproject.financialproject.ui.viewmodel.SUCCESS

class FragmentHome : Fragment() {

    private lateinit var fragmentHomeViewModel: FragmentHomeViewModel
    private lateinit var binding: FragmentHomeBinding

    @SuppressLint("FragmentLiveDataObserve")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        fragmentHomeViewModel = ViewModelProvider(this)[FragmentHomeViewModel::class.java]
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.viewModelFragmentHome = fragmentHomeViewModel
        binding.lifecycleOwner = this

        val adapter = MenuAdapter(context)
        binding.recyclerViewHistory.adapter = adapter
        binding.recyclerViewHistory.layoutManager = LinearLayoutManager(context)

        fragmentHomeViewModel.listInOut()

        fragmentHomeViewModel.listInOut2().observe(this,{
            adapter.addData(it.toList())
        })

        fragmentHomeViewModel.success.observe(this, {
            when (it) {
                SUCCESS.LOG_OUT_SUCCESS -> {
                    toast("sesion cerrada correctamente")
                }
                /*SUCCESS.LIST_IN_OUT -> {

                }*/
            }
        })

        fragmentHomeViewModel.navigation.observe(this, {
            when (it) {
                NAVIGATION.GO_LOGIN_VIEW -> {
                    val intent = Intent(context, LoginView::class.java)
                    context?.startActivity(intent)
                }
            }
        })

        //fragmentHomeViewModel.listInOut()

        // Inflate the layout for this fragment
        return binding.root


    }
}