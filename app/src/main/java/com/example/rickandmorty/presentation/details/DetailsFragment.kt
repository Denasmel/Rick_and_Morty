package com.example.rickandmorty.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.rickandmorty.databinding.FragmentDetailsBinding


class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        args.character.apply {
            Glide.with(requireContext()).load(image).into(binding.characterImageView)

            binding.nameTextView.text = name
            binding.originTextView.text = origin.name
            binding.statusTextView.text = status
            if (type.isEmpty())
                binding.typeLayout.visibility = View.GONE
            binding.typeTextView.text = type
            binding.speciesTextView.text = species
            binding.genderTextView.text = gender
            binding.locationTextView.text = location.name
            binding.createdTextView.text = created
        }

        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}