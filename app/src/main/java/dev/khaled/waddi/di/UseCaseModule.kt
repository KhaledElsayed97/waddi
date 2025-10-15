package dev.khaled.waddi.di

import dev.khaled.waddi.domain.repository.MainRepository
import dev.khaled.waddi.domain.usecases.GetPlacesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    
    @Provides
    @Singleton
    fun provideGetPlacesUseCase(
        repository: MainRepository
    ): GetPlacesUseCase {
        return GetPlacesUseCase(repository)
    }
}


