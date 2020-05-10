package com.aoshenfengyu.testamazingtalker.widget

import com.aoshenfengyu.testamazingtalker.bean.ScheduleBean
import java.util.*

class DateRangeComparator : Comparator<ScheduleBean.DateRangeBean> {
    override fun compare(
        sourceDateRange: ScheduleBean.DateRangeBean,
        destinationDateRange: ScheduleBean.DateRangeBean
    ): Int {
        val sourceDate = sourceDateRange.iso8601StartDateString!!
        val destinationDate = destinationDateRange.iso8601StartDateString!!
        return sourceDate.compareTo(destinationDate)
    }
}

