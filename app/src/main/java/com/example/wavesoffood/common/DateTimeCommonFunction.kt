package com.example.wavesoffood.common

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DateTimeCommonFunction {

    companion object {
        @RequiresApi(Build.VERSION_CODES.O)
        fun getDateTimeCurrent():String {
            val currentDateTime = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            val formattedDateTime = currentDateTime.format(formatter)
            return formattedDateTime.toString()
        }
    }
}