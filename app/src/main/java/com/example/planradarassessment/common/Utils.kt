package com.example.planradarassessment.common

import java.text.SimpleDateFormat
import java.util.*

object Utils {

    fun convertTimeStamp(timeStamp: Long) : String {
        val sdf = SimpleDateFormat("dd/MM/yyyy - HH:mm")
        return sdf.format(Date(timeStamp))
    }
}