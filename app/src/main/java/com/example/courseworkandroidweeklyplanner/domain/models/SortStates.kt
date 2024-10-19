package com.example.courseworkandroidweeklyplanner.domain.models

import com.example.courseworkandroidweeklyplanner.R

enum class SortStates(val description: Int)  {
    INCREASE(R.string.description_sort_priority_ascending),
    DECREASE(R.string.description_sort_priority_descending),
    STANDARD(R.string.description_no_sorting)
}