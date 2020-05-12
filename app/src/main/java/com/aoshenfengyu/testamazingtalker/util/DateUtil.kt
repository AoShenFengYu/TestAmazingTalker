package com.aoshenfengyu.testamazingtalker.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

object DateUtil {
    private const val FORMAT_ISO_8601 = "yyyy-MM-dd'T'HH:mm:ss'Z'"
    private const val FORMAT_DATE = "yyyy-MM-dd"
    private const val FORMAT_TIME = "HH:mm"

    @SuppressLint("SimpleDateFormat")
    fun getIso8601DateString(calendar: Calendar): String {
        val sdf = SimpleDateFormat(FORMAT_ISO_8601)
        sdf.timeZone = TimeZone.getTimeZone("GMT")
        return sdf.format(calendar.time)
    }

    @SuppressLint("SimpleDateFormat")
    fun getDate(iso8601String: String): Date {
        val sdf = SimpleDateFormat(FORMAT_ISO_8601)
        sdf.timeZone = TimeZone.getTimeZone("GMT")
        return sdf.parse(iso8601String)!!
    }

    @SuppressLint("SimpleDateFormat")
    fun getDateString(date: Date): String {
        val sdf = SimpleDateFormat(FORMAT_DATE)
        return sdf.format(date)
    }

    @SuppressLint("SimpleDateFormat")
    fun getTimeString(date: Date): String {
        val sdf = SimpleDateFormat(FORMAT_TIME)
        return sdf.format(date)
    }

    fun getTomorrowDate(date: Date): Date {
        val calendar = Calendar.getInstance()
        calendar.time = date
        calendar.add(Calendar.DAY_OF_YEAR, 1)
        return calendar.time
    }

    fun getLastDayOfWeek(startDate: Date): Date {
        val calendar = Calendar.getInstance()
        calendar.time = startDate
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY)
        calendar.set(Calendar.HOUR, 23)
        calendar.set(Calendar.MINUTE, 59)
        calendar.set(Calendar.SECOND, 59)
        calendar.set(Calendar.MILLISECOND, 999)
        return calendar.time
    }
}