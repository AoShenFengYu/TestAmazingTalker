package com.aoshenfengyu.testamazingtalker.mvp.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.aoshenfengyu.testamazingtalker.R
import com.aoshenfengyu.testamazingtalker.adapter.CalendarAdapter
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    private var pageChangeCallback: OnPageChangeCallback? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initCalendarViewPager()
        setTimezone()
    }

    private fun initCalendarViewPager() {
        val adapter = CalendarAdapter(this)
        vp2_calendar.adapter = adapter

        pageChangeCallback = object : OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                setDate(adapter.getStartDateCalendar(position))
            }
        }
        pageChangeCallback?.let {
            vp2_calendar.registerOnPageChangeCallback(it)
        }
    }

    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    private fun setDate(calendar: Calendar) {
        val sdf = SimpleDateFormat("yyyy/MM/dd")
        val startDateString = sdf.format(calendar.time)
        calendar.add(Calendar.DAY_OF_YEAR, 7)
        val firstDayOfNextWeek =
            calendar.get(Calendar.DAY_OF_MONTH).toString()
        tv_date_range.text = "$startDateString - $firstDayOfNextWeek"
    }

    private fun setTimezone() {
        val timezone = TimeZone.getDefault()
        tv_timezone.text =
            String.format(
                getString(R.string.timezone_string),
                timezone.id,
                timezone.getDisplayName(false, TimeZone.SHORT)
            )
    }

    override fun onDestroy() {
        super.onDestroy()
        pageChangeCallback?.let {
            vp2_calendar.unregisterOnPageChangeCallback(it)
        }
    }

}