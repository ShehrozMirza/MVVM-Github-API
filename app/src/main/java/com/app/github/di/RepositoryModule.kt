package com.app.github.di

import com.app.github.data.repository.DataRepository
import com.app.github.data.repository.DataRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindDataRepositoryModule(repository: DataRepositoryImpl) : DataRepository
}