package com.example.testadubrivny.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.testadubrivny.databinding.FragmentSecondBinding
import com.example.testadubrivny.vm.SecondViewModel


class SecondFragment : Fragment() {
    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel: SecondViewModel by viewModels()
        arguments?.let {
            val factId = SecondFragmentArgs.fromBundle(it).factId
            viewModel.getFactById(factId).observe(viewLifecycleOwner) { fact ->
                binding.factTextView.text = fact.fact
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}