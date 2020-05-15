package com.aoshenfengyu.testamazingtalker.mvp.model

import com.aoshenfengyu.testamazingtalker.bean.ScheduleBean
import com.aoshenfengyu.testamazingtalker.mvp.contract.CalendarContract
import com.aoshenfengyu.testamazingtalker.request.CalendarApi
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

open class CalendarModel(private val api: CalendarApi) : CalendarContract.Model {

    override fun requestSchedule(
        iso8601SundayString: String
    ): Observable<ScheduleBean> {
        return api
            .getSchedule("amy-estrada", iso8601SundayString)
            .subscribeOn(Schedulers.io())

    }

}