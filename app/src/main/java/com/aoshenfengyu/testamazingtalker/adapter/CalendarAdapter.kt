package com.aoshenfengyu.testamazingtalker.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.aoshenfengyu.testamazingtalker.mvp.fragment.CalendarFragment
import java.util.*

class CalendarAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return Integer.MAX_VALUE
    }

    override fun createFragment(position: Int): Fragment {
        return CalendarFragment(
            getStartDateCalendar(position)
        )
    }

    fun getStartDateCalendar(position: Int): Calendar {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.WEEK_OF_MONTH, position)
        calendar.set(Calendar.DAY_OF_WEEK, 1)
        calendar.add(Calendar.DAY_OF_YEAR, -1)
        calendar.set(Calendar.HOUR, 12)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return calendar
    }
}