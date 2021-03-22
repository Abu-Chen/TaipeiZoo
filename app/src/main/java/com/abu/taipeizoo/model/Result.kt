package com.abu.taipeizoo.model

import com.google.gson.annotations.SerializedName

/**
{
"result": {
"limit": 1000,
"offset": 0,
"count": 40,
"sort": "",
"results": [ {#Area/Plant}, {}... ]
}
}
 */

data class Result<T>(
    @SerializedName("result") var result: Data<T>? = null,
)

data class Data<T>(
    @SerializedName("limit") val limit: Int,
    @SerializedName("offset") val offset: Int,
    @SerializedName("count") val count: Int,
    @SerializedName("sort") val sort: String,
    @SerializedName("results") val results: ArrayList<T>,
)