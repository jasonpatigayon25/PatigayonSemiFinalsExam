package com.patigayon.semifinals.model

data class Tweet(
    val id: String,
    val name: String,
    val description: String,
    val timestamp: Long? = null
)