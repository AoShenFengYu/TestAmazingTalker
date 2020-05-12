package com.aoshenfengyu.testamazingtalker

import com.aoshenfengyu.testamazingtalker.bean.ScheduleBundle
import com.aoshenfengyu.testamazingtalker.mvp.contract.CalendarContract
import com.aoshenfengyu.testamazingtalker.mvp.model.CalendarModel
import com.aoshenfengyu.testamazingtalker.mvp.presenter.CalendarPresenter
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations


class CalendarPresenterTest {

    @Mock
    private lateinit var mockCalendarModel: CalendarModel

    @Mock
    private lateinit var mockCalendarView: CalendarContract.View

    private lateinit var calendarPresenter: CalendarPresenter

    private lateinit var scheduleBundle: ScheduleBundle

    private lateinit var startDateString: String

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        calendarPresenter = CalendarPresenter(mockCalendarView!!, mockCalendarModel!!)
        scheduleBundle = ScheduleBundle()
        startDateString = "2020-06-27T16:00:00Z"
    }

    @Test
    fun getScheduleFromModel() {
        calendarPresenter.getSchedule(startDateString)
        verify(mockCalendarModel)?.getSchedule(startDateString, calendarPresenter)
    }

    @Test
    fun onGetScheduleSuccessToView() {
        calendarPresenter.onGetScheduleSuccess(scheduleBundle)
        verify(mockCalendarView)?.setSchedule(scheduleBundle)
    }

}