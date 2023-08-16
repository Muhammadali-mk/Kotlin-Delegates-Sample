package com.example.kotlindelegates.util

import android.os.Build
import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.os.Parcelable
import java.io.Serializable

fun <T> Bundle.put(key: String, value: T) {
    when (value) {
        is Boolean -> putBoolean(key, value)
        is String -> putString(key, value)
        is Int -> putInt(key, value)
        is Short -> putShort(key, value)
        is Long -> putLong(key, value)
        is Byte -> putByte(key, value)
        is ByteArray -> putByteArray(key, value)
        is Char -> putChar(key, value)
        is CharArray -> putCharArray(key, value)
        is CharSequence -> putCharSequence(key, value)
        is Float -> putFloat(key, value)
        is Bundle -> putBundle(key, value)
        is Parcelable -> putParcelable(key, value)
        is Serializable -> putSerializable(key, value)
        else -> throw IllegalStateException("Type of property $key is not supported")
    }
}

inline fun <reified T> Bundle.get(key: String): T? {
    return when (T::class) {
        Boolean::class -> getBoolean(key)
        String::class -> getString(key)
        Int::class -> getInt(key)
        Short::class -> getShort(key)
        Long::class -> getLong(key)
        Byte::class -> getByte(key)
        ByteArray::class -> getByteArray(key)
        Char::class -> getChar(key)
        CharArray::class -> getCharArray(key)
        CharSequence::class -> getCharSequence(key)
        Float::class -> getFloat(key)
        Bundle::class -> getBundle(key)
        Parcelable::class -> parcelable(key)
        Serializable::class -> serializable(key)
        else -> throw IllegalStateException("Type of property $key is not found")
    } as? T
}

inline fun <reified T : Parcelable> Bundle.parcelable(key: String): T? = when {
    SDK_INT >= 33 -> getParcelable(key, T::class.java)
    else -> @Suppress("DEPRECATION") getParcelable(key) as? T
}

inline fun <reified T : Serializable> Bundle.serializable(key: String): T? = when {
    SDK_INT >= Build.VERSION_CODES.TIRAMISU -> getSerializable(key, T::class.java)
    else -> @Suppress("DEPRECATION") getSerializable(key) as? T
}