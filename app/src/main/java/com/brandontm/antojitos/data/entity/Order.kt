package com.brandontm.antojitos.data.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Order constructor(
    val name: String,
    val address: String,
    @Json(name = "phone_number") val phoneNumber: String,
    val cart: Map<Int, Int>)