package com.aoshenfengyu.testamazingtalker

import com.aoshenfengyu.testamazingtalker.util.DateUtil
import org.junit.Test
import java.util.*

class DateUtilTest {

    @Test
    fun getIso8601DateString() {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, 2020)
        calendar.set(Calendar.MONTH, 5)
        calendar.set(Calendar.DAY_OF_MONTH, 28)
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        val iso8601DateString = DateUtil.getIso8601DateString(calendar)
        assert(iso8601DateString == "2020-06-27T16:00:00Z")
    }
    @Test
    fun getDate() {
        val date = DateUtil.getDate("2020-06-27T16:00:00Z")
        assert(date.time == 1593273600000)
    }

    @Test
    fun getDateString() {
        val date = Date(1593273600000)
        val dateString = DateUtil.getDateString(date)
        assert(dateString == "2020-06-28")
    }

    @Test
    fun getTimeString() {
        val date = Date(1593273600000)
        val dateString = DateUtil.getTimeString(date)
        assert(dateString == "00:00")
    }

    @Test
    fun getTomorrowDate() {
        val date = Date(1593273600000)
        val tomorrowDate = DateUtil.getTomorrowDate(date)
        assert(tomorrowDate.time == 1593360000000)
    }
    @Test
    fun getLastDayOfWeek() {
        val date = Date(1593273600000)
        val tomorrowDate = DateUtil.getLastDayOfWeek(date)
        assert(tomorrowDate.time == 1593878399999)
    }
}