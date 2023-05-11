package com.example.rickandmorty.presentation.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickandmorty.R

class CharacterAdapter(
    private val characters: List<com.example.rickandmorty.data.model.Character>,
    private val characterClickListener: CharacterClickListener
) :
    RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val characterImageView: ImageView = itemView.findViewById(R.id.characterImageView)
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        val originTextView: TextView = itemView.findViewById(R.id.originTextView)
        val characterLayout: LinearLayout = itemView.findViewById(R.id.characterLayout)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.character_item, parent, false)
        return CharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = characters[position]

        Glide.with(holder.characterImageView.context).load(character.image)
            .into(holder.characterImageView)

        holder.nameTextView.text = character.name
        holder.originTextView.text = character.origin.name

        holder.characterLayout.setOnClickListener {
            characterClickListener.onClick(character)
        }
    }

    override fun getItemCount(): Int {
        return characters.size
    }
}
