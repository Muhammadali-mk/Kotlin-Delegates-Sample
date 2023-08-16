package com.example.kotlindelegates.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.kotlindelegates.R
import com.example.kotlindelegates.databinding.FragmentDataProviderBinding as ViewBinding

class DataProviderFragment : Fragment(R.layout.fragment_data_provider) {

    private var _binding: ViewBinding? = null
    private val binding: ViewBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.passDataBtn.setOnClickListener {
            parentFragmentManager.commit {
                replace(
                    R.id.fragment_container_view,
                    DataConsumerFragmentWithDelegate.newInstance(
                        age = binding.ageEt.text.toString().toInt(),
                        name = binding.nameEt.text.toString()
                    )
                )
                addToBackStack("")
            }
        }
    }
}