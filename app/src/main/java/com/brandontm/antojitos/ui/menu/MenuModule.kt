package com.brandontm.antojitos.ui.menu

import androidx.lifecycle.ViewModel
import com.brandontm.antojitos.di.scope.PerFragment
import com.brandontm.antojitos.di.viewModel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class MenuModule {
    @ContributesAndroidInjector
    @PerFragment
    internal abstract fun contributesMenuFragment(): MenuFragment

    @Binds
    @IntoMap
    @ViewModelKey(MenuViewModel::class)
    @PerFragment
    internal abstract fun bindMenuViewModel(contactListViewModel: MenuViewModel): ViewModel
}