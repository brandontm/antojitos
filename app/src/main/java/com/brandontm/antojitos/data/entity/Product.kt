package com.brandontm.antojitos.data.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Product(
    val id: Int,
    val name: String,
    val description: String,
    @Json(name = "image_url")
    val imageUrl: String?,
    val price: Int)