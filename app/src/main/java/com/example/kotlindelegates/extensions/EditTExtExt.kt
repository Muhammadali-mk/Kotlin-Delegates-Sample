package com.example.kotlindelegates.extensions

import android.text.Editable
import android.text.InputFilter
import android.text.TextUtils
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView

var EditText.distinctText: CharSequence
    get() = this.text.toString()
    set(value) {
        if (TextUtils.equals(value, this.text.toString())) return
        setTextDistinct(value)
    }

fun EditText.addInputFilter(filter: InputFilter) {
    filters = filters.toMutableList().apply { add(filter) }.toTypedArray()
}

fun EditText.setTextDistinct(newText: CharSequence): Boolean {
    val isCursorPosGreaterThanTextLen = selectionStart > newText.length
    val isCursorAtTheEnd = selectionStart == length()
    tag = false
    val isChanged = (this as TextView).setTextDistinct(newText)
    val isFormatterWork = newText.length < text.length
    val newCursorPos = when {
        isFormatterWork -> text.length
        isCursorPosGreaterThanTextLen || isCursorAtTheEnd -> newText.length
        else -> selectionStart
    }
    if (isChanged) setSelection(newCursorPos)
    tag = true
    return isChanged
}

fun EditText.setOnTextChanged(onTextChanged: (String) -> Unit) {
    var shouldSkipFirstEmit = true
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (tag != false && !shouldSkipFirstEmit) {
                removeTextChangedListener(this)
                onTextChanged.invoke(s?.toString().orEmpty())
                addTextChangedListener(this)
            }
            shouldSkipFirstEmit = false
        }

        override fun afterTextChanged(s: Editable?) {}
    })
}

fun TextView.setTextDistinct(newText: CharSequence): Boolean {
    if (TextUtils.equals(newText, text)) return false
    text = newText
    return true
}