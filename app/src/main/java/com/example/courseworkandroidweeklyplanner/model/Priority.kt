package com.example.courseworkandroidweeklyplanner.model

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