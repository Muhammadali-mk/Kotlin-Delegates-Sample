package com.example.kotlindelegates.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kotlindelegates.R
import com.example.kotlindelegates.databinding.FragmentDataConsumerBinding
import com.example.kotlindelegates.delegate.argumentDelegate
import com.example.kotlindelegates.util.RandomStudentDelegate

class DataConsumerFragmentWithDelegate : Fragment(R.layout.fragment_data_consumer) {

    private var _binding: FragmentDataConsumerBinding? = null
    private val binding get() = _binding!!

    private val randomStudent by RandomStudentDelegate()
    private var age by argumentDelegate<Int>()
    private var name by argumentDelegate<String>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDataConsumerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        renderDetails(age, name)
        binding.randomBtn.setOnClickListener {
            renderDetails(randomStudent.age, randomStudent.name)
        }
    }

    private fun renderDetails(age: Int?, name: String?) = with(binding) {
        agetTv.text = "Your age is $age"
        nameTv.text = "Your name is $name"
    }

    companion object {
        fun newInstance(age: Int, name: String): DataConsumerFragmentWithDelegate {
            return DataConsumerFragmentWithDelegate()
                .apply {
                    this.age = age
                    this.name = name
                }
        }
    }
}