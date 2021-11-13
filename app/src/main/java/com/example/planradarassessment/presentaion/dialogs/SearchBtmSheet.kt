package com.example.planradarassessment.presentaion.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.planradarassessment.common.Resource
import com.example.planradarassessment.databinding.BtmSheetSearchBinding
import com.example.planradarassessment.presentaion.cities.CitiesViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchBtmSheet : BottomSheetDialogFragment() {

    private lateinit var binding: BtmSheetSearchBinding
    private val viewModel: CitiesViewModel by activityViewModels()


    companion object {
        val TAG = "SearchBtmSheet"
        fun newInstance() = SearchBtmSheet()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BtmSheetSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.etSearch.setOnEditorActionListener { view, action, event ->
            viewModel.addCity(binding.etSearch.text.toString())
            dismiss()
            return@setOnEditorActionListener action == EditorInfo.IME_ACTION_SEARCH
        }
//        viewModel.state.observe(viewLifecycleOwner) {
//            when (it) {
//                is Resource.Success ->
//                    Toast.makeText(requireContext(), "success", Toast.LENGTH_SHORT).show()
//                is Resource.Error -> Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT)
//                    .show()
//                is Resource.Loading -> Toast.makeText(
//                    requireContext(),
//                    "loading...",
//                    Toast.LENGTH_SHORT
//                ).show()
//            }
//        }
    }
}