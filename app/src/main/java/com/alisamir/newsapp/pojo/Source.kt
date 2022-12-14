package com.alisamir.newsapp.pojo

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Source(
    @Json(name = "id")
    val id: @RawValue Any?,
    @Json(name = "name")
    val name: String?
):Parcelable




