package com.example.kotlindelegates.util

import kotlin.properties.ReadWriteProperty
import kotlin.random.Random
import kotlin.reflect.KProperty

class UpperCaseDelegate : ReadWriteProperty<Any?, String> {
    private var value: String? = null

    override operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return value ?: throw IllegalStateException(
            "Property ${property.name} should be initialized before get."
        )
    }

    override operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        this.value = value.uppercase()
    }
}

fun main() {
    var uppercase by UpperCaseDelegate()
    uppercase = "Muhammadali"
    println(uppercase) //MUHAMMADALI
}

data class Student(val age: Int, val name: String)

class RandomStudentDelegate {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): Student {
        return Student(
            age = Random.nextInt(15, 80),
            name = generateRandomString()
        )
    }
}

fun generateRandomString(): String {
    val charPool: List<Char> = ('A'..'Z') + ('a'..'z') + ('0'..'9')
    return (1..8)
        .map { i -> Random.nextInt(0, charPool.size) }
        .map(charPool::get)
        .joinToString("")
}