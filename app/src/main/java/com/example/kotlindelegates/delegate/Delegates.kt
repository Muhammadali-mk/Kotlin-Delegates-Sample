package com.example.kotlindelegates.delegate

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.kotlindelegates.util.get
import com.example.kotlindelegates.util.put
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

inline fun <reified T> argumentDelegate(): ReadWriteProperty<Fragment, T?> =
    object : ReadWriteProperty<Fragment, T?> {

        override fun getValue(
            thisRef: Fragment,
            property: KProperty<*>
        ): T? {
            val key = property.name
            return thisRef.arguments?.get<T>(key)
        }

        override fun setValue(
            thisRef: Fragment,
            property: KProperty<*>, value: T?
        ) {
            val args = thisRef.arguments
                ?: Bundle().also(thisRef::setArguments)
            val key = property.name
            value?.let { args.put(key, it) } ?: args.remove(key)
        }
    }