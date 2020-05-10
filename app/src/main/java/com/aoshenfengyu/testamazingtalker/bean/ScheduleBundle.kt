package com.aoshenfengyu.testamazingtalker.bean

class ScheduleBundle {

    private val scheduleItem = ArrayList<ScheduleItem>()
    private val columnTitleEnables = ArrayList<Boolean>()

    fun setScheduleItems(scheduleItem: ArrayList<ScheduleItem>) {
        this.scheduleItem.addAll(scheduleItem)
    }

    fun getScheduleItems(): ArrayList<ScheduleItem> {
        return scheduleItem
    }

    fun setColumnTitleEnables(columnTitleEnables: ArrayList<Boolean>) {
        this.columnTitleEnables.addAll(columnTitleEnables)
    }

    fun getColumnTitleEnables(): ArrayList<Boolean> {
        return columnTitleEnables
    }

    fun isEmpty(): Boolean {
        return scheduleItem.size == 0
    }
}