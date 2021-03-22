package com.abu.taipeizoo.model

import android.util.Log
import com.abu.taipeizoo.extension.TAG
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.OkHttpClient
import okhttp3.Request

class TaipeiDataRepo {
    companion object {
        private const val URL = "https://data.taipei/api/v1/dataset/"
        private const val AREA = "5a0e5fbb-72f8-41c6-908e-2fb25eff9b8a?scope=resourceAquire"
        private const val PLANT = "f18de02f-b6c9-47c0-8cda-50efad621c14?scope=resourceAquire"

        private const val URL_AREA = URL + AREA
        private const val URL_PLANT = URL + PLANT

        private const val QUERY = "q"
        private const val DEFAULT_CONDITION = "&offset=0&limit=1000"
    }

    fun getZooAreaList(): ArrayList<Area>? {
        val url = URL_AREA + DEFAULT_CONDITION
        val result = downloadResults(url)
        Log.d(TAG, "result:$result")
        val r: Result<Area>? = try {
            Gson().fromJson(result, object : TypeToken<Result<Area>>() {}.type)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
        Log.d(TAG, "areaResult:$r")
        return r?.result?.results
    }

    fun getPlantByArea(area: String): List<Plant> {
        return arrayListOf()
    }

    private fun downloadResults(url: String): String? {
        Log.d(TAG, "downloadResults:$url")
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient.Builder().build()
        var result: String? = null
        try {
            val response = client.newCall(request).execute()
            response?.body?.let { body ->
                result = body.string()
                body.close()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            //TODO: Error handle
        }
        return result
    }
}