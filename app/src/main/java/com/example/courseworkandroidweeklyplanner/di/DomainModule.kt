package com.example.courseworkandroidweeklyplanner.di

import com.example.courseworkandroidweeklyplanner.domain.interactors.CalendarInteractor
import com.example.courseworkandroidweeklyplanner.domain.usecases.ChangeExpandDayCardUseCase
import com.example.courseworkandroidweeklyplanner.domain.usecases.GetWeekUseCase
import com.example.courseworkandroidweeklyplanner.domain.usecases.GetWeekDaysUseCase
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
    fun provideChangeExpandDayCardUseCase() = ChangeExpandDayCardUseCase()

    @Provides
    fun provideCalendarInteractor() = CalendarInteractor()

}