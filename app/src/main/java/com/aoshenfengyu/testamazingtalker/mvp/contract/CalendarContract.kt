package com.aoshenfengyu.testamazingtalker.mvp.contract

import com.aoshenfengyu.testamazingtalker.bean.ScheduleBundle

class CalendarContract {

    interface View {
        fun setSchedule(scheduleBundle: ScheduleBundle)
    }

    interface Model {
        fun getSchedule(
            iso8601SundayString: String,
            callback: Callback
        )
    }

    interface Callback {
        fun onGetScheduleSuccess(scheduleBundle: ScheduleBundle)
        fun onGetScheduleFailure()
    }

}