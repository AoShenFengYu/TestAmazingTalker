package com.aoshenfengyu.testamazingtalker.adapter

import android.graphics.Typeface
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aoshenfengyu.testamazingtalker.R
import com.aoshenfengyu.testamazingtalker.bean.ScheduleItem

class ScheduleAdapter : RecyclerView.Adapter<ScheduleAdapter.Holder>() {
    companion object {
        const val VIEW_TYPE_TIME = 0
        const val VIEW_TYPE_SPACE = 1
    }

    private val scheduleItems = ArrayList<ScheduleItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val v =
            if (viewType == VIEW_TYPE_TIME) {
                View.inflate(parent.context, R.layout.item_time, null)
            } else {
                View.inflate(parent.context, R.layout.item_space, null)
            }
        return Holder(v, viewType)
    }

    override fun getItemCount(): Int {
        return scheduleItems.size
    }

    override fun getItemViewType(position: Int): Int {
        return scheduleItems[position].viewType
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        if (holder.viewType == VIEW_TYPE_TIME) {
            holder.bind(scheduleItems[position])
        }
    }

    fun setSchedule(scheduleItems: ArrayList<ScheduleItem>) {
        this.scheduleItems.clear()
        this.scheduleItems.addAll(scheduleItems)
        notifyDataSetChanged()
    }

    class Holder(v: View, val viewType: Int) : RecyclerView.ViewHolder(v) {
        private val tvTime = v.findViewById<TextView>(R.id.tv_time)

        fun bind(item: ScheduleItem) {
            tvTime.text = item.timeString
            tvTime.isEnabled = item.isEnable

            val typeface = if (item.isEnable) Typeface.BOLD else Typeface.NORMAL
            tvTime.setTypeface(
                tvTime.typeface,
                typeface
            )
        }
    }
}