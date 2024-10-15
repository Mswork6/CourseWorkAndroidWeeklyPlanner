package com.example.courseworkandroidweeklyplanner.domain.interactors

import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

class CalendarInteractor {
    // Состояние для отображения календаря
    private val isCalendarVisibleInternal: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isCalendarVisible: StateFlow<Boolean> = isCalendarVisibleInternal.asStateFlow()

    // Выбранная дата
    private val selectedDateInternal: MutableStateFlow<LocalDate?> = MutableStateFlow(null)
    val selectedDate: StateFlow<LocalDate?> = selectedDateInternal.asStateFlow()

    // Функция для открытия календаря
    fun openCalendar() = isCalendarVisibleInternal.update { true }

    // Функция для закрытия календаря без сохранения
    fun dismissCalendar() = isCalendarVisibleInternal.update { false }

    /**
     * Функция для подтверждения выбора даты.
     * */
    fun confirmDate(dateMillis: Long?) {
        dateMillis?.let {
            selectedDateInternal.update {
                Instant.ofEpochMilli(dateMillis)
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate()
            }
        }
        dismissCalendar()
    }
}
