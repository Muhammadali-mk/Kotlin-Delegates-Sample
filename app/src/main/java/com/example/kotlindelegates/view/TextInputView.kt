package com.example.kotlindelegates.view

import android.content.Context
import android.text.InputType.TYPE_TEXT_VARIATION_NORMAL
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.core.content.withStyledAttributes
import androidx.core.view.isVisible
import com.example.kotlindelegates.R
import com.example.kotlindelegates.databinding.ItemTextInputBinding
import com.example.kotlindelegates.extensions.distinctText
import com.example.kotlindelegates.extensions.setOnTextChanged


class TextInputView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    var editable: Boolean = false

    var distinctText: String?
        get() = binding.inputEt.distinctText.toString()
        set(value) {
            if (editable) {
                binding.inputEt.isVisible = true
                binding.inputTv.isVisible = false
                binding.inputEt.distinctText = value.toString()
            } else {
                binding.inputEt.isVisible = false
                binding.inputTv.isVisible = true
                binding.inputTv.text = value
            }
        }

    var labelText: String = ""
        set(value) {
            field = value
            if (value.isEmpty())
                binding.labelTv.visibility = GONE
            else
                binding.labelTv.text = value
        }

    var errorText: String? = null
        set(value) {
            field = value
            renderErrorText()
        }


    val binding by lazy(LazyThreadSafetyMode.NONE) { ItemTextInputBinding.bind(this) }

    init {
        inflate(context, R.layout.item_text_input, this)
        orientation = VERTICAL
        initAttrs(attrs)
    }

    private fun initAttrs(attrs: AttributeSet?) {
        attrs ?: return
        context.withStyledAttributes(attrs, R.styleable.TextInputView) {
            with(binding) {
                  inputEt.inputType = getInteger(
                      R.styleable.TextInputView_android_inputType,
                      TYPE_TEXT_VARIATION_NORMAL
                  )
                labelText = getString(R.styleable.TextInputView_labelText).orEmpty()
                labelTv.text = labelText
                inputEt.hint =
                    getString(R.styleable.TextInputView_android_hint).orEmpty()
            }
        }
    }

    fun setText(value: String?) {
        value?.let {
            setDisabled()
            binding.inputTv.text = value
        } ?: setEnabled()
    }


    fun addTextChangedListener(listener: (String) -> Unit) {
        binding.inputEt.setOnTextChanged { listener(it) }
    }

    private fun setDisabled() {
        binding.inputTv.isVisible = true
        binding.inputEt.isVisible = false
    }

    private fun setEnabled() {
        binding.inputEt.isVisible = true
        binding.inputTv.isVisible = false
    }

    private fun renderErrorText() = with(binding.errorTv) {
        isVisible = errorText != null
        text = errorText
    }
}