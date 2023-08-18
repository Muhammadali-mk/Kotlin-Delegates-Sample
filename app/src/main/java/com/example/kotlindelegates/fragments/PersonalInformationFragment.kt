package com.example.kotlindelegates.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kotlindelegates.R
import com.example.kotlindelegates.controllers.FirstNameItemController
import com.example.kotlindelegates.databinding.FragmentPersonalInfoBinding
import com.example.kotlindelegates.ui.ItemsUi
import com.example.kotlindelegates.ui.ItemsUi.FirstName
import ru.surfstudio.android.easyadapter.EasyAdapter
import ru.surfstudio.android.easyadapter.ItemList

class PersonalInformationFragment : Fragment(R.layout.fragment_personal_info) {
    private var _binding: FragmentPersonalInfoBinding? = null
    private val binding get() = _binding!!
    private val adapter = EasyAdapter()
    private val firstNameController = FirstNameItemController {

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPersonalInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRv()
        renderItems(
            listOf(
                FirstName(
                    value = "Muhammadali",
                    labelText = getString(R.string.first_name_hint),
                    errorText = null,
                    isEditable = false
                )
            )
        )
    }

    private fun renderItems(uiItems: List<ItemsUi>) {
        val itemList = ItemList()
        uiItems.forEach {
            when (it) {

                is FirstName -> itemList.add(it, firstNameController)
                else -> {}
            }
        }
        adapter.setItems(itemList)
    }

    private fun initRv() = with(binding) {
        contentRv.itemAnimator = null
        contentRv.adapter = adapter
        /*contentRv.addItemDecoration(
            Decorator.Builder()
                .offset(titleController.viewType() to OffsetDecorator(top = 24.toPx))
                .offset(headerController.viewType() to OffsetDecorator(top = 24.toPx))
                .overlay(
                    headerController.viewType() to DashedDividerDecorator(
                        topOffset = 27.toPx
                    )
                )
                .offset(physicalContentController.viewType() to OffsetDecorator(top = 56.toPx))
                .offset(juridicalContentController.viewType() to OffsetDecorator(top = 56.toPx))
                .offset(footerController.viewType() to OffsetDecorator(top = 35.toPx))
                .build()
        )*/
    }

}