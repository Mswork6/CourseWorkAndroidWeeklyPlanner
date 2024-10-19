package com.example.courseworkandroidweeklyplanner.domain.models

enum class PriorityStateEnum(override val description: String) : StateInterface {
    HIGH("Высокий"),
    STANDART("Стандартный"),
    LOW("Низкий")
}