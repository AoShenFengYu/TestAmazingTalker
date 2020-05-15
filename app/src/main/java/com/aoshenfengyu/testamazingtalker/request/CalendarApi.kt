package com.aoshenfengyu.testamazingtalker.request

import com.aoshenfengyu.testamazingtalker.bean.ScheduleBean
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CalendarApi {
    companion object {
        const val PATH_GET_SCHEDULE = "v1/guest/teachers/{user_name}/schedule"
    }

    @GET(PATH_GET_SCHEDULE)
    fun getSchedule(
        @Path("user_name") userName: String,
        @Query("started_at") iso8601DateString: String
    ): Observable<ScheduleBean>

}