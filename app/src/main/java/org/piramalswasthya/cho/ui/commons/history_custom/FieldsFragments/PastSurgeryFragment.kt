package org.piramalswasthya.cho.ui.commons.history_custom.FieldsFragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.LinearLayout
import dagger.hilt.android.AndroidEntryPoint
import org.piramalswasthya.cho.databinding.FragmentPastSurgeryBinding
import org.piramalswasthya.cho.ui.HistoryFieldsInterface

@AndroidEntryPoint
class PastSurgeryFragment() : Fragment() {

    private val HoSurgery = arrayOf(
                "Appendicectomy",
                "Nill",
                "Other",
                "Tonsilectomy"
    )

    private val TimePeriodAgo = arrayOf(
        "Day(s)",
        "Week(s)",
        "Month(s)",
        "Year(s)"
    )
    private var _binding: FragmentPastSurgeryBinding? = null
    private val binding: FragmentPastSurgeryBinding
        get() = _binding!!

    private lateinit var dropdownSurgery: AutoCompleteTextView
    private lateinit var dropdownTimePeriodAgo: AutoCompleteTextView
    private var historyListener: HistoryFieldsInterface? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       _binding = FragmentPastSurgeryBinding.inflate(inflater,container,false)
        return binding.root
    }
    private var fragmentTag :String? = null
    fun setFragmentTag(tag:String){
        fragmentTag = tag
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dropdownSurgery = binding.surgeryText
        dropdownTimePeriodAgo = binding.dropdownDurUnit
        val surgeryAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, HoSurgery)
        dropdownSurgery.setAdapter(surgeryAdapter)
        val timePeriodAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line,TimePeriodAgo)
        dropdownTimePeriodAgo.setAdapter(timePeriodAdapter)

        binding.deleteButton.setOnClickListener {
            fragmentTag?.let {
                historyListener?.onDeleteButtonClickedSurgery(it)
            }
        }
        binding.plusButton.setOnClickListener {
            fragmentTag?.let {
                historyListener?.onAddButtonClickedSurgery(it)
            }
        }

        binding.plusButton.isEnabled = false
        binding.resetButton.isEnabled = false
        binding.dropdownDurUnit.addTextChangedListener(inputTextWatcher)
        binding.inputDuration.addTextChangedListener(inputTextWatcher)
        binding.surgeryText.addTextChangedListener(inputTextWatcher)
        binding.resetButton.setOnClickListener {
            binding.dropdownDurUnit.text?.clear()
            binding.inputDuration.text?.clear()
            binding.surgeryText.text?.clear()
        }
    }

    fun setListener(listener: HistoryFieldsInterface) {
        this.historyListener = listener
    }

    private val inputTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            // No action needed
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            updateAddAndResetButtonState()
        }

        override fun afterTextChanged(s: Editable?) {
            // No action needed
        }
    }

    private fun updateAddAndResetButtonState() {
        val durationUnit = binding.dropdownDurUnit.text.toString().trim()
        val duration = binding.inputDuration.text.toString().trim()
        val chiefComplaint = binding.surgeryText.text.toString().trim()
        binding.plusButton.isEnabled = durationUnit.isNotEmpty()&&duration.isNotEmpty()&&chiefComplaint.isNotEmpty()
        binding.resetButton.isEnabled = durationUnit.isNotEmpty()&&duration.isNotEmpty()&&chiefComplaint.isNotEmpty()
    }

}