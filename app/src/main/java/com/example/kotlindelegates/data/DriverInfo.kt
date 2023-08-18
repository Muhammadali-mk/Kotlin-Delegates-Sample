package com.example.kotlindelegates.data

data class DriverInfo(
    val firstName: String? = null,
    val lastName: String? = null,
    val phone: String? = null,
    val address: Address
)

data class Address(
    var city: String?,
    var line1: String?,
    var line2: String?,
    var state: String?,
    var zip: String?
)