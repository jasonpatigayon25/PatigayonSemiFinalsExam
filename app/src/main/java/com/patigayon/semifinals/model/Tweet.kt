package com.patigayon.semifinals.model

data class Tweet(
    val id: String,
    val name: String,
    val description: String,
    val timestamp: Timestamp? = null
)

data class Timestamp(
    val seconds: Long? = null,
    val nanoseconds: Int? = null
)
