package com.brandontm.antojitos.ui.cart

import androidx.lifecycle.ViewModel
import com.brandontm.antojitos.di.scope.PerActivity
import com.brandontm.antojitos.di.scope.PerFragment
import com.brandontm.antojitos.di.viewModel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class ShoppingCartModule {
    @ContributesAndroidInjector
    @PerFragment
    internal abstract fun contributesShoppingCartFragment(): ShoppingCartFragment

    @Binds
    @IntoMap
    @ViewModelKey(ShoppingCartViewModel::class)
    @PerFragment
    internal abstract fun bindShoppingCartViewModel(shoppingCartViewModel: ShoppingCartViewModel): ViewModel
}
