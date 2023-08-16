package com.example.kotlindelegates.util

import kotlin.properties.Delegates
import kotlin.random.Random
import kotlin.reflect.KProperty

/*

class PropertyDelegate<T>(private var value: T? = null) {

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T? {
        println("property get value: $value")
        return value
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T?) {
        println("property set value from ${this.value} to $value")
        this.value = value
    }
}

class Delegate {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "$thisRef, thank you for delegating '${property.name}' to me!"
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("$value has been assigned to '${property.name} in $thisRef.'")
    }
}

//data class Student(val id: Int, val name: String)

class RandomStudentDelegate {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): Student {
        return Student(
            id = Random.nextInt(),
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

class PlusOneProperty : ReadWriteProperty<Any?, Int> {

    private var mValue = 0

    override fun getValue(thisRef: Any?, property: KProperty<*>): Int {
        return mValue
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Int) {
        mValue = value + 1
    }
}
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
    println(uppercase)//MUHAMMADALI
}
fun View.isVisible(keepBounds: Boolean): ReadWriteProperty<Any, Boolean> =
    object : ReadWriteProperty<Any, Boolean> {
        override fun getValue(
            thisRef: Any,
            property: KProperty<*>
        ): Boolean = visibility == View.VISIBLE

        override fun setValue(
            thisRef: Any,
            property: KProperty<*>,
            value: Boolean
        ) {
            visibility = when {
                value -> View.VISIBLE
                keepBounds -> View.INVISIBLE
                else -> View.GONE
            }
        }
    }*//*
class Student {
    var name: String by Delegates.observable("Empty") {
            prop, old, new ->
        println("$old -> $new")
    }
    var address: String by Delegates.vetoable("") {
            property, oldValue, newValue ->
        newValue.length > 4
    }
}

fun test() {
    val student = Student()
    student.name = "John"//  Empty->John
    student.name = "Bill"// John->Bill
    student.address = "New York"
    student.address = "New"
    println(student.address)//New York
}*/

data class Student(val age: Int, val name: String)

class RandomStudentDelegate {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): Student {
        return Student(
            age = Random.nextInt(15,80),
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