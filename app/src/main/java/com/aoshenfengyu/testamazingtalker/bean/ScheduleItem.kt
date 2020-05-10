package com.aoshenfengyu.testamazingtalker.bean

import com.aoshenfengyu.testamazingtalker.adapter.ScheduleAdapter.Companion.VIEW_TYPE_SPACE
import com.aoshenfengyu.testamazingtalker.adapter.ScheduleAdapter.Companion.VIEW_TYPE_TIME

class ScheduleItem() {

    var viewType = VIEW_TYPE_SPACE
    var dateString: String? = null
    var timeString: String? = null
    var isEnable = false

    constructor(dateString: String, timeString: String, enable: Boolean) : this() {
        viewType = VIEW_TYPE_TIME
        this.dateString = dateString
        this.timeString = timeString
        this.isEnable = enable
    }

    override fun toString(): String {
        return "ScheduleItem(viewType=$viewType, dateString=$dateString, timeString=$timeString, isEnable=$isEnable)"
    }

}