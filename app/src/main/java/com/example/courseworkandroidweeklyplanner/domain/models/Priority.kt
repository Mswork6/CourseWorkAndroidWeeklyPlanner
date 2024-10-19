package com.example.courseworkandroidweeklyplanner.domain.models

import com.example.courseworkandroidweeklyplanner.R

enum class Priority (val description: Int)  {
    HIGH(R.string.description_high),
    BASIC(R.string.description_basic),
    LOW(R.string.description_low)
}