package com.example.courseworkandroidweeklyplanner.domain.models

enum class Priority (override val description: String) : StateInterface {
    HIGH("Высокий"),
    BASIC("Стандартный"),
    LOW("Низкий")
}