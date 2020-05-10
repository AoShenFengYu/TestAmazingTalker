package com.aoshenfengyu.testamazingtalker.mvp.presenter

import com.aoshenfengyu.testamazingtalker.base.BasePresenter
import com.aoshenfengyu.testamazingtalker.bean.ScheduleBundle
import com.aoshenfengyu.testamazingtalker.mvp.model.CalendarModel
import com.aoshenfengyu.testamazingtalker.mvp.contract.CalendarContract

class CalendarPresenter : BasePresenter<CalendarContract.View>(), CalendarContract.Callback {

    private val model: CalendarContract.Model =
        CalendarModel()

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