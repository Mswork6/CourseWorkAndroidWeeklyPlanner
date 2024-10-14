package com.example.courseworkandroidweeklyplanner.di

import com.example.courseworkandroidweeklyplanner.domain.usecases.ChangeExpandDayCardUseCase
import com.example.courseworkandroidweeklyplanner.domain.usecases.GetCurrentWeekUseCase
import com.example.courseworkandroidweeklyplanner.domain.usecases.GetWeekDaysUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideGetCurrentWeekUseCase() : GetCurrentWeekUseCase {
        return GetCurrentWeekUseCase()
    }

    @Provides
    fun provideGetWeekDaysUseCase() : GetWeekDaysUseCase {
        return GetWeekDaysUseCase()
    }

    @Provides
    fun provideChangeExpandDayCardUseCase() : ChangeExpandDayCardUseCase {
        return ChangeExpandDayCardUseCase()
    }

}