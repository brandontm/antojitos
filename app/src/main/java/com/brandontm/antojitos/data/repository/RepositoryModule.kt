package com.brandontm.antojitos.data.repository

import com.brandontm.antojitos.data.network.AntojitosApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideUserRepository(antojitosApi: AntojitosApi): ProductRepository {
        return ProductRepository.getInstance(antojitosApi)
    }
}
