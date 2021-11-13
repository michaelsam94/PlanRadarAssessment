package com.example.planradarassessment.presentaion.cities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.planradarassessment.common.Resource
import com.example.planradarassessment.databinding.FragmentCitiesBinding
import com.example.planradarassessment.domain.model.City
import com.example.planradarassessment.presentaion.dialogs.SearchBtmSheet
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CitiesFragment : Fragment() {

    private lateinit var binding: FragmentCitiesBinding
    private val viewModel: CitiesViewModel by activityViewModels()
    private lateinit var adapter: CitiesAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCitiesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fabAdd.setOnClickListener {
            val searchDialog = SearchBtmSheet.newInstance()
            searchDialog.show(parentFragmentManager, SearchBtmSheet.TAG)
        }

        adapter = CitiesAdapter(mutableListOf())
        adapter.onCityClick = ::onCityClick
        adapter.onHistoryClick = ::onHistoryClick
        binding.rvCities.adapter = adapter
        binding.rvCities.layoutManager = LinearLayoutManager(requireContext())


        viewModel.saveCitiesState.observe(this as LifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    adapter.addAll(it.data!!)
                }
                is Resource.Error -> Toast.makeText(
                    requireContext(),
                    it.message,
                    Toast.LENGTH_SHORT
                )
                    .show()
                is Resource.Loading -> Toast.makeText(
                    requireContext(),
                    "loading...",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        viewModel.searchCityState.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    adapter.addCity(it.data!!)
                }
                is Resource.Error -> Toast.makeText(
                    requireContext(),
                    it.message,
                    Toast.LENGTH_SHORT
                )
                    .show()
                is Resource.Loading -> Toast.makeText(
                    requireContext(),
                    "loading...",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun onCityClick(city: City) {
        val action =
            CitiesFragmentDirections.actionCitiesFragmentToDetailsFragment(cityName = city.name)
        findNavController().navigate(action)
    }

    private fun onHistoryClick(city: City) {
        val action =
            CitiesFragmentDirections.actionCitiesFragmentToHistoryFragment(cityName = city.name)
        findNavController().navigate(action)
    }

}