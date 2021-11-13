package com.example.planradarassessment.presentaion.history

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.planradarassessment.common.Resource
import com.example.planradarassessment.databinding.FragmentHistoryBinding
import com.example.planradarassessment.presentaion.cities.CitiesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryFragment : Fragment() {

    private lateinit var binding: FragmentHistoryBinding
    private val viewModel: HistoryViewModel by viewModels()
    private lateinit var adapter: HistoryAdapter
    private val args: HistoryFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHistoryBinding.inflate(inflater,container,false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvCityName.text = "${args.cityName} historical"
        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }

        adapter = HistoryAdapter(mutableListOf())
        binding.rvHistory.adapter = adapter
        binding.rvHistory.layoutManager = LinearLayoutManager(requireContext())

        viewModel.getSavedHistory(args.cityName)
        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    adapter.addAll(it.data!!)
                }
                is Resource.Error -> Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT)
                    .show()
                is Resource.Loading -> Toast.makeText(
                    requireContext(),
                    "loading...",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}