package com.brandontm.antojitos.ui.checkout

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI

import com.brandontm.antojitos.R
import com.brandontm.antojitos.base.BaseFragment
import com.brandontm.antojitos.di.viewModel.ViewModelProviderFactory
import kotlinx.android.synthetic.main.checkout_fragment.*
import timber.log.Timber
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

        initTextInputFields()

        btn_checkout.setOnClickListener {
            if(validateTextInput()) {
                val name = txt_name.text.toString()
                val address = txt_name.text.toString()
                val phoneNumber = txt_name.text.toString()

                viewModel.placeOrder(name, address, phoneNumber)
            }
        }

        loadObservers()
    }

    private fun loadObservers() {
        viewModel.orderStatus.observe(viewLifecycleOwner) { orderSuccess ->
            if(orderSuccess) {
                Toast.makeText(
                    requireContext().applicationContext,
                    getString(R.string.order_placed),
                    Toast.LENGTH_LONG
                ).show()
            } else {
                Toast.makeText(
                    requireContext().applicationContext,
                    getString(R.string.place_order_error),
                    Toast.LENGTH_LONG
                ).show()
            }

            findNavController().popBackStack(R.id.menuFragment, false)
        }
    }

    private fun validateTextInput(): Boolean {
        var valid = true

        if(txt_name.text.isNullOrBlank() || txt_name.text?.length?.compareTo(6) == -1) {
            input_name.error = getString(R.string.error_minimum_length_name)
            valid = false
        }

        if(txt_address.text.isNullOrBlank() || txt_address.text?.length?.compareTo(16) == -1) {
            input_address.error = getString(R.string.error_minimum_length_address)
            valid = false
        }

        if(txt_phone_number.text.isNullOrBlank() || txt_phone_number.text?.length?.compareTo(8) == -1) {
            input_phone_number.error = getString(R.string.error_minimum_length_phone)
            valid = false
        }

        return valid
    }

    private fun initTextInputFields() {
        txt_name.doOnTextChanged { _, _, _, _ -> input_name.error = "" }

        txt_address.doOnTextChanged { _, _, _, _ -> input_address.error = "" }

        txt_phone_number.doOnTextChanged { _, _, _, _ -> input_phone_number.error = "" }

        txt_name.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if(!hasFocus) {
                if (txt_name.text.isNullOrBlank() || txt_name.text?.length?.compareTo(6) == -1) {
                    input_name.error = getString(R.string.error_minimum_length_name)
                }
            }
        }

        txt_address.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if(!hasFocus) {
                if (txt_address.text.isNullOrBlank() || txt_address.text?.length?.compareTo(16) == -1) {
                    input_address.error = getString(R.string.error_minimum_length_address)
                }
            }
        }

        txt_phone_number.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if(!hasFocus) {
                if (txt_phone_number.text.isNullOrBlank() || txt_phone_number.text?.length?.compareTo(
                        8
                    ) == -1
                ) {
                    input_phone_number.error = getString(R.string.error_minimum_length_phone)
                }
            }
        }
    }
}
