package com.brandontm.antojitos.di.application

import android.app.Application
import com.brandontm.antojitos.AntojitosApp
import com.brandontm.antojitos.data.repository.RepositoryModule
import com.brandontm.antojitos.di.activity.ActivityBuildersModule
import com.brandontm.antojitos.di.network.AntojitosApiModule
import com.brandontm.antojitos.di.viewModel.ViewModelFactoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ApplicationModule::class,
    AndroidSupportInjectionModule::class,
    ViewModelFactoryModule::class,
    ActivityBuildersModule::class,
    AntojitosApiModule::class,
    RepositoryModule::class
])
interface ApplicationComponent : AndroidInjector<AntojitosApp> {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): ApplicationComponent
    }
}
