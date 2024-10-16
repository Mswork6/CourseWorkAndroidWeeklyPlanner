package com.example.courseworkandroidweeklyplanner.di

import com.example.courseworkandroidweeklyplanner.domain.interactors.CalendarInteractor
import com.example.courseworkandroidweeklyplanner.domain.usecases.GetWeekDaysUseCase
import com.example.courseworkandroidweeklyplanner.domain.usecases.GetWeekUseCase
import com.example.courseworkandroidweeklyplanner.domain.usecases.UpdateWeekDaysUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {
    @Provides
    fun provideGetCurrentWeekUseCase() = GetWeekUseCase()

    @Provides
    fun provideGetWeekDaysUseCase() = GetWeekDaysUseCase()

    @Provides
    fun provideUpdateWeekDaysUseCase() = UpdateWeekDaysUseCase()

    @Provides
    fun provideCalendarInteractor() = CalendarInteractor()
}