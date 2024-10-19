package com.example.courseworkandroidweeklyplanner.domain.models

enum class SortStateEnum(override val description: String) : StateInterface {
    INCREASE("По возрастанию приоритета"),
    DECREASE("По убыванию приоритета"),
    STANDART("Без сортировки")
}