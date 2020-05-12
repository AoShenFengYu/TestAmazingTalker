package com.aoshenfengyu.testamazingtalker.mvp.presenter

import com.aoshenfengyu.testamazingtalker.base.BasePresenter
import com.aoshenfengyu.testamazingtalker.bean.ScheduleBundle
import com.aoshenfengyu.testamazingtalker.mvp.contract.CalendarContract
import org.jetbrains.annotations.TestOnly

open class CalendarPresenter(private val model: CalendarContract.Model) :
    BasePresenter<CalendarContract.View>(), CalendarContract.Callback {

    @TestOnly
    constructor(view: CalendarContract.View, model: CalendarContract.Model) : this(model) {
        attachView(view)
    }

    fun getSchedule(iso8601SundayString: String) {
        model.getSchedule(iso8601SundayString, this)
    }

    override fun onGetScheduleSuccess(scheduleBundle: ScheduleBundle) {
        view?.setSchedule(scheduleBundle)
    }

    override fun onGetScheduleFailure() {
        view?.setSchedule(ScheduleBundle())
    }
}