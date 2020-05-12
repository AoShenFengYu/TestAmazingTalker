package com.aoshenfengyu.testamazingtalker.request

import android.content.Context
import android.text.TextUtils
import com.aoshenfengyu.testamazingtalker.util.FileUtil.getString
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import java.io.File
import java.io.IOException

class MockResponseInterceptor(private val context: Context) : Interceptor {
    companion object {
        private val TAG = MockResponseInterceptor::class.java.simpleName
        private const val ASSET_DIR_NAME = "mock"
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url
        val uri = url.toUri()
        val path = uri.path
        return interceptRequestAndMockResponse(chain, path)!!
    }

    private fun interceptRequestAndMockResponse(
        chain: Interceptor.Chain,
        path: String
    ): Response? {
        var response: Response? = null
        val request = chain.request()
        if (path.contains(
                CalendarApi.PATH_GET_SCHEDULE.replace("{user_name}", "amy-estrada")
            )
        ) {
            response = getMockResponse(request, "get_schedule.json")
        }
        return response
    }

    private fun getMockResponse(
        request: Request,
        assetFileName: String
    ): Response? {
        val data: String
        val assetManager = context.assets
        data = getString(assetManager.open(ASSET_DIR_NAME + File.separator + assetFileName))
        val response: Response
        response = createHttpResponse(request, data)
        return response
    }

    private fun createHttpResponse(
        request: Request,
        dataJson: String
    ): Response {
        return if (TextUtils.isEmpty(dataJson)) {
            Response.Builder()
                .code(500)
                .protocol(Protocol.HTTP_1_0)
                .request(request)
                .build()
        } else {
            Response.Builder()
                .code(200)
                .message(dataJson)
                .request(request)
                .protocol(Protocol.HTTP_1_0)
                .addHeader("Content-Type", "application/json")
                .body(dataJson.toResponseBody("application/json".toMediaTypeOrNull()))
                .build()
        }
    }

}