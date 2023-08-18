package com.example.kotlindelegates.controllers

import android.view.ViewGroup
import com.example.kotlindelegates.R
import com.example.kotlindelegates.ui.ItemsUi
import com.example.kotlindelegates.view.TextInputView
import ru.surfstudio.android.easyadapter.controller.BindableItemController
import ru.surfstudio.android.easyadapter.holder.BindableViewHolder

internal class FirstNameItemController(
    private val onEdited: (String) -> Unit
) : BindableItemController<ItemsUi.FirstName, FirstNameItemController.Holder>() {

    override fun getItemId(data: ItemsUi.FirstName) = ID_TAG

    override fun createViewHolder(parent: ViewGroup) = Holder(parent)

    inner class Holder(parent: ViewGroup) : BindableViewHolder<ItemsUi.FirstName>(parent, R.layout.item_first_name) {
        private val firstNameInputView = itemView as TextInputView

        init {
            firstNameInputView.addTextChangedListener {
                onEdited(it)
            }
        }

        override fun bind(data: ItemsUi.FirstName) = with(firstNameInputView) {
            editable = data.isEditable
            labelText = data.labelText.orEmpty()
            distinctText = data.value
            errorText = data.errorText
        }
    }

    private companion object {
        const val ID_TAG = "FullNameItemController"
    }
}