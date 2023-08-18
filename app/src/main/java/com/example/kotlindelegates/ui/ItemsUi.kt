package com.example.kotlindelegates.ui

sealed interface ItemsUi {
    data class FirstName(
        val value: String?,
        val labelText: String?,
        val errorText: String?,
        val isEditable: Boolean = false
    ) : ItemsUi
}