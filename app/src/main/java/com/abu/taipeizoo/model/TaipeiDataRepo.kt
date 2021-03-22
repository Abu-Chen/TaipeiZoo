package com.abu.taipeizoo.model

import android.util.Log
import com.abu.taipeizoo.extension.TAG
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.OkHttpClient
import okhttp3.Request
import java.lang.reflect.Type

class TaipeiDataRepo {
    companion object {
        private const val URL = "https://data.taipei/api/v1/dataset/"
        private const val AREA = "5a0e5fbb-72f8-41c6-908e-2fb25eff9b8a?scope=resourceAquire"
        private const val PLANT = "f18de02f-b6c9-47c0-8cda-50efad621c14?scope=resourceAquire"

        private const val URL_AREA = URL + AREA
        private const val URL_PLANT = URL + PLANT

        private const val DEFAULT_CONDITION = "&offset=0&limit=1000"
        private val QUERY_CONDITION: (query: String) -> String = { "&q=$it" }

        private const val RETRY_LIMIT = 3
    }

    fun getAreaList(): ArrayList<Area>? {
        val url = URL_AREA + DEFAULT_CONDITION
        val type = object : TypeToken<Result<Area>>() {}.type
        return getResultList<Result<Area>?>(url, type)?.data?.results
    }

    fun getPlantListByArea(area: String): ArrayList<Plant>? {
        val url = URL_PLANT + DEFAULT_CONDITION + QUERY_CONDITION(area)
        val type = object : TypeToken<Result<Plant>>() {}.type
        return getResultList<Result<Plant>>(url, type)?.data?.results
    }

    private fun <T> getResultList(url: String, type: Type): T? {
        val result = downloadResults(url)?.replace("\ufeff", "")
        //Log.d(TAG, "getResultList:$result")
        return try {
            Gson().fromJson(result, type)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private fun downloadResults(url: String): String? {
        Log.d(TAG, "downloadResults:$url")
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient.Builder().build()
        var exeCount = 0
        var result: String? = null
        do {
            exeCount++
            try {
                val response = client.newCall(request).execute()
                response.body?.let { body ->
                    result = body.string()
                    body.close()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        } while (result == null && exeCount < RETRY_LIMIT)
        return result
    }
}