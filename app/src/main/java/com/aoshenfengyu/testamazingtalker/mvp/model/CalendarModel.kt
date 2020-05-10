package com.aoshenfengyu.testamazingtalker.mvp.model

import com.aoshenfengyu.testamazingtalker.bean.ScheduleBean
import com.aoshenfengyu.testamazingtalker.factory.ScheduleItemsFactory
import com.aoshenfengyu.testamazingtalker.mvp.contract.CalendarContract
import com.aoshenfengyu.testamazingtalker.request.CalendarApi
import com.aoshenfengyu.testamazingtalker.request.RequestManager
import com.aoshenfengyu.testamazingtalker.widget.DateRangeComparator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

class CalendarModel : CalendarContract.Model {

    override fun getSchedule(
        iso8601SundayString: String,
        callback: CalendarContract.Callback
    ) {
        RequestManager
            .getInstance()
            .retrofit
            .create(CalendarApi::class.java)
            .getSchedule("amy-estrada", iso8601SundayString)
            .enqueue(object : Callback<ScheduleBean> {
                override fun onFailure(call: Call<ScheduleBean>, t: Throwable) {
                    callback.onGetScheduleFailure()
                }

                override fun onResponse(
                    call: Call<ScheduleBean>,
                    response: Response<ScheduleBean>
                ) {
                    val timeRanges = sortTimeRanges(response.body())
                    val scheduleBundleFactory = ScheduleItemsFactory()
                    val scheduleBundle = scheduleBundleFactory.create(iso8601SundayString, timeRanges)
                    callback.onGetScheduleSuccess(scheduleBundle)
                }
            })
    }

    private fun sortTimeRanges(schedule: ScheduleBean?): ArrayList<ScheduleBean.DateRangeBean> {
        val dateRanges = ArrayList<ScheduleBean.DateRangeBean>()

        schedule?.availableDateRanges?.let {
            dateRanges.addAll(it)
        }

        schedule?.bookedDateRanges?.let {
            dateRanges.addAll(it)
        }

        Collections.sort(dateRanges, DateRangeComparator())

        return dateRanges
    }
}