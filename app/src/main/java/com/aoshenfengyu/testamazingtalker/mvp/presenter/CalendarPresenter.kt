package com.aoshenfengyu.testamazingtalker.mvp.presenter

import com.aoshenfengyu.testamazingtalker.base.BasePresenter
import com.aoshenfengyu.testamazingtalker.bean.ScheduleBean
import com.aoshenfengyu.testamazingtalker.bean.ScheduleBundle
import com.aoshenfengyu.testamazingtalker.factory.ScheduleItemsFactory
import com.aoshenfengyu.testamazingtalker.mvp.contract.CalendarContract
import com.aoshenfengyu.testamazingtalker.widget.DateRangeComparator
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers


open class CalendarPresenter(
    private val view: CalendarContract.View,
    private val model: CalendarContract.Model
) :
    BasePresenter<CalendarContract.View>(), CalendarContract.Callback {

    fun loadSchedule(iso8601SundayString: String) {

        model.requestSchedule(iso8601SundayString)
            .subscribeOn(Schedulers.computation())
            .flatMap { scheduleBean ->
                val dateRanges = ArrayList<ScheduleBean.DateRangeBean>()
                scheduleBean?.availableDateRanges?.let { it ->
                    dateRanges.addAll(it)
                }
                scheduleBean?.bookedDateRanges?.let { it ->
                    dateRanges.addAll(it)
                }
                return@flatMap Observable.fromIterable(dateRanges)
            }
            .filter {
                !it.isEmpty()
            }
            .toSortedList(DateRangeComparator())
            .map {
                val scheduleBundleFactory = ScheduleItemsFactory()
                val arrayList = ArrayList(it)
                return@map scheduleBundleFactory.create(iso8601SundayString, arrayList)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { onGetScheduleSuccess(it) },
                { onGetScheduleFailure() }
            )

    }

    override fun onGetScheduleSuccess(scheduleBundle: ScheduleBundle) {
        view.setSchedule(scheduleBundle)
    }

    override fun onGetScheduleFailure() {
        view.setSchedule(ScheduleBundle())
    }
}