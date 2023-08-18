package com.example.kotlindelegates.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.kotlindelegates.R
import com.example.kotlindelegates.databinding.FragmentDataConsumerBinding

class DataConsumerFragment : Fragment(R.layout.fragment_data_consumer) {

    private var _binding: FragmentDataConsumerBinding? = null
    private val binding get() = _binding!!
    private var age: Int? = null
    private var name: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDataConsumerBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        age = arguments?.getInt(EXTRA_AGE)
        name = arguments?.getString(EXTRA_NAME)
        binding.agetTv.text = "Your age is $age"
        binding.nameTv.text = "Your name is $name"
        Log.i("DataProviderFragment", " id: $age and name: $name")
    }

    companion object {
        private const val EXTRA_AGE = "age"
        private const val EXTRA_NAME = "name"

        fun newInstance(age: Int, name: String): DataConsumerFragment {
            return DataConsumerFragment()
                .apply {
                    arguments = bundleOf(EXTRA_AGE to age, EXTRA_NAME to name)
                }
        }
    }
}