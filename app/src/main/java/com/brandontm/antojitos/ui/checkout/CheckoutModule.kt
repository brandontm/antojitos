package com.brandontm.antojitos.ui.checkout

import androidx.lifecycle.ViewModel
import com.brandontm.antojitos.di.scope.PerFragment
import com.brandontm.antojitos.di.viewModel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap


@Module
abstract class CheckoutModule {
    @ContributesAndroidInjector
    @PerFragment
    internal abstract fun contributesCheckoutFragment(): CheckoutFragment

    @Binds
    @IntoMap
    @ViewModelKey(CheckoutViewModel::class)
    @PerFragment
    internal abstract fun bindCheckoutViewModel(checkoutViewModel: CheckoutViewModel): ViewModel
}
