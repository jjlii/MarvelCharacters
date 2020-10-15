package com.example.marvelcharacters.ui.list_character

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.domain.Character
import com.example.marvelcharacters.R
import com.example.marvelcharacters.getImage
import kotlinx.android.synthetic.main.characters_item.view.*
import java.text.SimpleDateFormat

class CharactersListAdapter(private val clickListener: (Int?)-> Unit): ListAdapter<Character, CharactersListAdapter.CharactersViewHolder>(DiffCallback()) {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=
        CharactersViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.characters_item,parent,false))

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }


    inner class CharactersViewHolder(view: View): RecyclerView.ViewHolder(view){
        fun bind(character: Character, clickListener: (Int)-> Unit){
            with(itemView) {
                val sdf = SimpleDateFormat("MMM dd yyyy")
                val resultDate = character.modified
                val img =  character.thumbnail?.getImage()
                character_name_value.text = character.name
                n_comics_value.text = character.comicList?.items?.size.toString()
                n_stories_value.text = character.stories?.items?.size.toString()
                n_events_value.text = character.events?.items?.size.toString()
                n_series_value.text = character.series?.items?.size.toString()
                last_updated_value.text = sdf.format(resultDate)?: ""
                Glide.with(this)
                    .load(img)
                    .placeholder(R.drawable.ic_android_black_24dp)
                    .apply(RequestOptions.overrideOf(600,600))
                    .skipMemoryCache(true)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(character_photo)

                setOnClickListener{
                    clickListener(character.id)
                }
            }
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<Character>(){
    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem == newItem
    }

}