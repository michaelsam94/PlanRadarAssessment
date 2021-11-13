package com.example.planradarassessment.presentaion.details

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.planradarassessment.common.Constant
import com.example.planradarassessment.common.Resource
import com.example.planradarassessment.common.Utils
import com.example.planradarassessment.databinding.FragmentCitiesBinding
import com.example.planradarassessment.databinding.FragmentDetailsBinding
import com.example.planradarassessment.domain.model.City
import com.example.planradarassessment.presentaion.cities.CitiesAdapter

import com.example.planradarassessment.presentaion.cities.CitiesViewModel
import com.example.planradarassessment.presentaion.dialogs.SearchBtmSheet
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    private val viewModel: DetailsViewModel by viewModels()
    private val args: DetailsFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }
        viewModel.getCity(args.cityName)
        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    bindData(it.data!!)
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

    @SuppressLint("SetTextI18n")
    internal fun bindData(city: City) {
        binding.tvCityName.text = city.name
        Glide.with(binding.ivCurrentCondition).load(Constant.getIcon(city.icon)).into(binding.ivCurrentCondition)
        binding.tvCondition.text = city.description
        binding.tvTemp.text = "${city.temp} °C"
        binding.tvHumidity.text = "${city.humidity} %"
        binding.tvWindSpeed.text = "${city.windSpeed} km/h"
        binding.tvInfoReceived.text = "Weather information for ${city.name} received on\n" +
                Utils.convertTimeStamp(System.currentTimeMillis())
    }
}