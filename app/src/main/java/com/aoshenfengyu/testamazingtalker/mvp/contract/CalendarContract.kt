package com.aoshenfengyu.testamazingtalker.mvp.contract

import com.aoshenfengyu.testamazingtalker.bean.ScheduleBean
import com.aoshenfengyu.testamazingtalker.bean.ScheduleBundle
import io.reactivex.rxjava3.core.Observable

class CalendarContract {

    interface View {
        fun setSchedule(scheduleBundle: ScheduleBundle)
    }

    interface Model {
        fun requestSchedule(
            iso8601SundayString: String
        ): Observable<ScheduleBean>
    }

    interface Callback {
        fun onGetScheduleSuccess(scheduleBundle: ScheduleBundle)
        fun onGetScheduleFailure()
    }

}