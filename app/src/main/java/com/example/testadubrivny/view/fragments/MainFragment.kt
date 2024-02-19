package com.example.testadubrivny.view.fragments

import android.os.Bundle
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.marginTop
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.testadubrivny.db.NumberFact
import com.example.testadubrivny.databinding.FragmentMainBinding
import com.example.testadubrivny.vm.MainViewModel

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding.getFactButton.setOnClickListener {
            val number = binding.numberInput.text.toString()
            if (number.isNotBlank()) viewModel.getFactForNumber(number)
        }

        binding.getRandomFactButton.setOnClickListener {
            viewModel.getRandomFact()
        }

        viewModel.allFacts.observe(viewLifecycleOwner) { facts ->
            updateFactsUI(facts)
        }
    }

    private fun updateFactsUI(facts: List<NumberFact>) {
        binding.linearLayoutFacts.removeAllViews()
        facts.forEach { fact ->
            val factTextView = TextView(context).apply {
                binding.latestFactTextView.text = fact.fact

                text =
                    "${fact.id}: ${fact.fact}"
                textSize = 16f
                maxLines = 1
                val marginBottomInPixels = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    16f,
                    resources.displayMetrics
                ).toInt()
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    bottomMargin = marginBottomInPixels
                }
                setOnClickListener {
                    val action =
                        MainFragmentDirections.actionMainFragmentToSecondFragment(factId = fact.id)
                    findNavController().navigate(action)

                }
            }
            binding.linearLayoutFacts.addView(factTextView)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}