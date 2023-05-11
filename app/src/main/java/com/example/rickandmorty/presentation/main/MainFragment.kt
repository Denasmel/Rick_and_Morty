package com.example.rickandmorty.presentation.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.data.model.Character
import com.example.rickandmorty.databinding.FragmentMainBinding
import com.example.rickandmorty.presentation.MainActivity
import com.example.rickandmorty.presentation.core.RickAndMortyViewModelFactory


class MainFragment : Fragment(), CharacterClickListener {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    private val listOfCharacters = mutableListOf<Character>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModelFactory = RickAndMortyViewModelFactory()
        val viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        val adapter = CharacterAdapter(listOfCharacters, this)
        val layoutManager = LinearLayoutManager(requireContext())
        val dividerItemDecoration = DividerItemDecoration(
            binding.characterRecyclerView.context,
            layoutManager.orientation
        )
        binding.characterRecyclerView.addItemDecoration(dividerItemDecoration)
        binding.characterRecyclerView.layoutManager = layoutManager
        binding.characterRecyclerView.adapter = adapter


        binding.characterRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    viewModel.loadCharacters()
                }
            }
        })

        viewModel.characterLiveData.observe(viewLifecycleOwner) {
            val startPosition = listOfCharacters.size
            listOfCharacters.addAll(it)
            adapter.notifyItemRangeInserted(startPosition, it.size)
            binding.progressBar.visibility = View.GONE
            if (listOfCharacters.isEmpty()) {
                showTryAgainButton()
            }
        }
    }

    private fun showTryAgainButton() {
        binding.tryAgainButton.visibility = View.VISIBLE
        binding.characterRecyclerView.visibility = View.GONE
        binding.errorTextView.visibility = View.VISIBLE
        binding.tryAgainButton.setOnClickListener {
            val intent = Intent(requireContext(), MainActivity::class.java)
            requireActivity().startActivity(intent)
            requireActivity().finishAffinity()
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    override fun onClick(character: Character) {
        findNavController().navigate(
            MainFragmentDirections.actionMainFragmentToDetailsFragment(character)
        )
    }

}

interface CharacterClickListener {
    fun onClick(character: Character)
}