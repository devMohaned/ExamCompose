package com.dev.exam.core.util

import com.google.gson.annotations.SerializedName

data class WrappedListResponse<T> (
    var code: Int,
    @SerializedName("message") var message : String,
    @SerializedName("data") var data : List<T>? = null
)


data class WrappedResponse<T> (
    var code: Int,
    @SerializedName("message") var message : String,
    @SerializedName("data") var data : T? = null
)