package com.aoshenfengyu.testamazingtalker.mvp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import com.aoshenfengyu.testamazingtalker.R
import com.aoshenfengyu.testamazingtalker.adapter.ScheduleAdapter
import com.aoshenfengyu.testamazingtalker.base.BaseFragment
import com.aoshenfengyu.testamazingtalker.bean.ScheduleBundle
import com.aoshenfengyu.testamazingtalker.bean.ScheduleItem
import com.aoshenfengyu.testamazingtalker.constant.Constant.CALENDAR_COLUMN_COUNT
import com.aoshenfengyu.testamazingtalker.mvp.contract.CalendarContract
import com.aoshenfengyu.testamazingtalker.mvp.model.CalendarModel
import com.aoshenfengyu.testamazingtalker.mvp.presenter.CalendarPresenter
import com.aoshenfengyu.testamazingtalker.util.DateUtil
import kotlinx.android.synthetic.main.fragment_calendar.*
import java.util.*
import kotlin.collections.ArrayList

class CalendarFragment(private val startDateCalendar: Calendar) :
    BaseFragment<CalendarContract.View, CalendarPresenter>(),
    CalendarContract.View {

    private var ivDayTitleLines = ArrayList<ImageView>()
    private var tvDayTitles = ArrayList<TextView>()
    private var tvDayTexts = ArrayList<TextView>()

    override fun onCreatePresenter(): CalendarPresenter {
        return CalendarPresenter(CalendarModel())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_calendar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        findViews()
        setDayTexts()
    }

    override fun onResume() {
        super.onResume()
        getSchedule()
    }

    private fun findViews() {
        ivDayTitleLines.addAll(
            listOf<ImageView>(
                iv_day_title_line_sun,
                iv_day_title_line_mon,
                iv_day_title_line_tue,
                iv_day_title_line_wed,
                iv_day_title_line_thu,
                iv_day_title_line_fri,
                iv_day_title_line_sat
            )
        )
        tvDayTitles.addAll(
            listOf<TextView>(
                tv_day_title_text_sun,
                tv_day_title_text_mon,
                tv_day_title_text_tue,
                tv_day_title_text_wed,
                tv_day_title_text_thu,
                tv_day_title_text_fri,
                tv_day_title_text_sat
            )
        )
        tvDayTexts.addAll(
            listOf<TextView>(
                tv_day_text_sun,
                tv_day_text_mon,
                tv_day_text_tue,
                tv_day_text_wed,
                tv_day_text_thu,
                tv_day_text_fri,
                tv_day_text_sat
            )
        )
    }

    private fun setDayTexts() {
        for ((i, tv) in tvDayTexts.withIndex()) {
            val calendar = startDateCalendar.clone() as Calendar
            calendar.add(Calendar.DAY_OF_MONTH, i)
            tv.text = calendar.get(Calendar.DAY_OF_MONTH).toString()
        }
    }

    private fun getSchedule() {
        presenter?.getSchedule(
            DateUtil.getIso8601DateString(startDateCalendar)
        )
    }

    override fun setSchedule(scheduleBundle: ScheduleBundle) {
        cpb_loading.visibility = View.GONE
        if (scheduleBundle.isEmpty()) {
            return
        }
        setDayTitleEnable(scheduleBundle.getColumnTitleEnables())
        setScheduleList(scheduleBundle.getScheduleItems())
    }

    private fun setDayTitleEnable(enables: ArrayList<Boolean>) {
        for ((i, enable) in enables.withIndex()) {
            ivDayTitleLines[i].isEnabled = enable
            tvDayTitles[i].isEnabled = enable
            tvDayTexts[i].isEnabled = enable
        }
    }

    private fun setScheduleList(scheduleItems: ArrayList<ScheduleItem>) {
        rv_schedule.layoutManager = GridLayoutManager(
            context,
            CALENDAR_COLUMN_COUNT,
            GridLayoutManager.VERTICAL,
            false
        )

        val adapter = ScheduleAdapter()
        rv_schedule.adapter = adapter
        adapter.setSchedule(scheduleItems)
    }

}