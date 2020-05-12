package com.aoshenfengyu.testamazingtalker.util

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

object FileUtil {
    @JvmStatic
    @Throws(IOException::class)
    fun getString(inputStream: InputStream): String {
        val stringBuilder = StringBuilder()
        val inputStreamReader = InputStreamReader(inputStream)
        val bufferedReader = BufferedReader(inputStreamReader)
        var lineString: String
        while (bufferedReader.readLine().also { lineString = it } != null) {
            stringBuilder.append(lineString)
        }
        inputStream.close()
        return stringBuilder.toString()
    }
}