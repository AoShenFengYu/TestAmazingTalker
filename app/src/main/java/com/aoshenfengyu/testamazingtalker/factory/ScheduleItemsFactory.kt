package com.aoshenfengyu.testamazingtalker.factory

import com.aoshenfengyu.testamazingtalker.constant.Constant.CALENDAR_COLUMN_COUNT
import com.aoshenfengyu.testamazingtalker.constant.Constant.CALENDAR_TIME_INTERVAL_MINUTE
import com.aoshenfengyu.testamazingtalker.bean.ScheduleBean
import com.aoshenfengyu.testamazingtalker.bean.ScheduleBundle
import com.aoshenfengyu.testamazingtalker.bean.ScheduleItem
import com.aoshenfengyu.testamazingtalker.util.DatetimeUtil
import java.util.*
import kotlin.collections.ArrayList

class ScheduleItemsFactory {
    private var today = Date()
    private var todayString = ""
    private var todayItems = ArrayList<ScheduleItem>()

    fun create(
        iso8601SundayString: String,
        dateRanges: ArrayList<ScheduleBean.DateRangeBean>
    ): ScheduleBundle {
        val twoDimensionalScheduleItems = createScheduleItemsArray(iso8601SundayString, dateRanges)
        val scheduleItems = twoDimensionalScheduleItems.flat()
        val columnTitleEnables = twoDimensionalScheduleItems.getColumnTitleEnables()
        val bundle = ScheduleBundle()
        bundle.setScheduleItems(scheduleItems)
        bundle.setColumnTitleEnables(columnTitleEnables)
        return bundle
    }

    private fun createScheduleItemsArray(
        iso8601SundayString: String,
        dateRanges: ArrayList<ScheduleBean.DateRangeBean>
    ): TwoDimensionalScheduleItems {
        val twoDimensionalScheduleItems = TwoDimensionalScheduleItems()
        if (dateRanges.size == 0) return twoDimensionalScheduleItems

        val sunday = DatetimeUtil.getDate(iso8601SundayString)
        val lastDayOfWeek = DatetimeUtil.getLastDayOfWeek(sunday)

        startDay(sunday)

        for (dateRange in dateRanges) {
            val startDate = DatetimeUtil.getDate(dateRange.iso8601StartDateString!!)
            val endDate = DatetimeUtil.getDate(dateRange.iso8601EndDateString!!)
            val scheduleItems =
                createScheduleItems(startDate, endDate, dateRange.isEnable, lastDayOfWeek)
            groupScheduleItems(twoDimensionalScheduleItems, scheduleItems)
        }

        // add last day items of week to itemsArray
        if (!twoDimensionalScheduleItems.isFull()) {
            twoDimensionalScheduleItems.add(todayItems)
        }

        twoDimensionalScheduleItems.fillEveryDayWithSpaceItems()
        return twoDimensionalScheduleItems
    }

    private fun groupScheduleItems(
        twoDimensionalScheduleItems: TwoDimensionalScheduleItems,
        scheduleItems: java.util.ArrayList<ScheduleItem>
    ) {
        for (scheduleItem in scheduleItems) {
            while (!twoDimensionalScheduleItems.isFull()) {
                if (todayString == scheduleItem.dateString) {
                    break

                } else {
                    val isLastDayOfWeek = endToday(twoDimensionalScheduleItems)
                    if (isLastDayOfWeek) {
                        break
                    }
                }
            }
            todayItems.add(scheduleItem)
        }
    }

    private fun createScheduleItems(
        startDate: Date,
        endDate: Date,
        isEnable: Boolean,
        lastDayOfWeek: Date
    ): ArrayList<ScheduleItem> {
        val scheduleItems = ArrayList<ScheduleItem>()
        val endDate =
            if (endDate.before(lastDayOfWeek)) endDate else lastDayOfWeek
        val intervalDateCalendar = Calendar.getInstance()
        intervalDateCalendar.time = startDate

        while (intervalDateCalendar.time.before(endDate)) {
            val scheduleItem = createScheduleItem(intervalDateCalendar.time, isEnable)
            scheduleItems.add(scheduleItem)
            intervalDateCalendar.add(Calendar.MINUTE, CALENDAR_TIME_INTERVAL_MINUTE)
        }
        return scheduleItems
    }

    private fun createScheduleItem(date: Date, isEnable: Boolean): ScheduleItem {
        return ScheduleItem(
            DatetimeUtil.getDateString(date),
            DatetimeUtil.getTimeString(date),
            isEnable
        )
    }

    private fun startDay(sunday: Date) {
        today = sunday
        todayString = DatetimeUtil.getDateString(today)
        todayItems = ArrayList<ScheduleItem>()
    }

    /**
     * @return is last day of week?
     * **/
    private fun endToday(twoDimensionalScheduleItems: TwoDimensionalScheduleItems): Boolean {
        twoDimensionalScheduleItems.add(todayItems)
        if (twoDimensionalScheduleItems.isFull()) {
            return true
        }

        today = DatetimeUtil.getTomorrowDate(today)
        todayString = DatetimeUtil.getDateString(today)
        todayItems = ArrayList<ScheduleItem>()
        return false
    }

    inner class TwoDimensionalScheduleItems {
        private val itemsArray = ArrayList<ArrayList<ScheduleItem>>()
        private val columnTitleEnables = ArrayList<Boolean>()

        fun add(scheduleItem: java.util.ArrayList<ScheduleItem>) {
            itemsArray.add(scheduleItem)
        }

        fun isFull(): Boolean {
            return itemsArray.size == CALENDAR_COLUMN_COUNT
        }

        fun fillEveryDayWithSpaceItems() {
            var maxSize = 0
            for (items in itemsArray) {
                if (items.size > maxSize) {
                    maxSize = items.size
                }
            }
            for (items in itemsArray) {
                columnTitleEnables.add(items.size != 0)
                if (items.size < maxSize) {
                    for (j in items.size until maxSize) {
                        items.add(ScheduleItem())
                    }
                }
            }
        }

        fun flat(): ArrayList<ScheduleItem> {
            val items = ArrayList<ScheduleItem>()
            val columnCount = itemsArray[0].size
            for (i in 0 until columnCount) {
                for (scheduleItems in itemsArray) {
                    items.add(scheduleItems[i])
                }
            }
            return items
        }

        fun getColumnTitleEnables(): ArrayList<Boolean> {
            return columnTitleEnables
        }
    }
}