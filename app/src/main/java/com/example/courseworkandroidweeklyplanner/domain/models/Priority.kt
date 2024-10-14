package com.example.courseworkandroidweeklyplanner.domain.models

enum class Priority {
    LOW,
    BASIC,
    HIGH;

    override fun toString(): String {
        return when(this) {
            LOW -> "Низкий"
            BASIC -> "Стандартный"
            HIGH -> "Высокий"
        }
    }
}