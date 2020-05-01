package com.brandontm.antojitos.ui.checkout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI

import com.brandontm.antojitos.R
import com.brandontm.antojitos.base.BaseFragment
import com.brandontm.antojitos.di.viewModel.ViewModelProviderFactory
import kotlinx.android.synthetic.main.checkout_fragment.*
import javax.inject.Inject

class CheckoutFragment : BaseFragment() {
    @Inject
    lateinit var viewModelProvider: ViewModelProviderFactory

    private val viewModel by viewModels<CheckoutViewModel> {
        viewModelProvider
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.checkout_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val navHostFragment = NavHostFragment.findNavController(this)
        NavigationUI.setupWithNavController(toolbar, navHostFragment)
    }
}
