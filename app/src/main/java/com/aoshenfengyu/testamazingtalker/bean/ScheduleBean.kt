package com.aoshenfengyu.testamazingtalker.bean

import com.google.gson.annotations.SerializedName

class ScheduleBean {
    @SerializedName("available")
    var availableDateRanges: List<AvailableBean>? = null

    @SerializedName("booked")
    var bookedDateRanges: List<BookedBean>? = null

    open class DateRangeBean {
        @SerializedName("start")
        var iso8601StartDateString: String? = null

        @SerializedName("end")
        var iso8601EndDateString: String? = null

        var isEnable: Boolean = false

        fun isEmpty(): Boolean {
            return iso8601StartDateString == null || iso8601EndDateString == null
        }
    }

    class AvailableBean : DateRangeBean() {
        /**
         * start : 2020-05-09T13:00:00Z
         * end : 2020-05-09T14:00:00Z
         */
        init {
            isEnable = true
        }

        override fun toString(): String {
            return "AvailableBean(start=$iso8601StartDateString, end=$iso8601EndDateString, enable=$iso8601EndDateString)"
        }

    }

    class BookedBean : DateRangeBean() {
        /**
         * start : 2020-05-09T14:00:00Z
         * end : 2020-05-09T16:00:00Z
         */
        init {
            isEnable = false
        }

        override fun toString(): String {
            return "BookedBean(start=$iso8601StartDateString, end=$iso8601EndDateString)"
        }

    }

    override fun toString(): String {
        return "ScheduleBean(available=$availableDateRanges, booked=$bookedDateRanges)"
    }
}