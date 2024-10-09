package com.example.courseworkandroidweeklyplanner.model

import java.time.format.DateTimeFormatter

class DateTimeFormat {

    companion object{
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    }

}