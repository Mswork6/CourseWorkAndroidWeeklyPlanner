package com.example.courseworkandroidweeklyplanner.di

import com.example.courseworkandroidweeklyplanner.data.repository.TaskRepositoryInteractor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun providesTaskRepositoryInteractor() = TaskRepositoryInteractor()

}