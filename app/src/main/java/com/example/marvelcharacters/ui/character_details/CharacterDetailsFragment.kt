package com.example.marvelcharacters.ui.character_details

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.domain.Character
import com.example.marvelcharacters.R
import com.example.marvelcharacters.getImage
import com.example.marvelcharacters.ui.base.BaseFragment
import kotlinx.android.synthetic.main.characters_item.view.*
import kotlinx.android.synthetic.main.fragment_character_details.*

class CharacterDetailsFragment : BaseFragment<CharacterDetailsViewModel>() {

    override fun getLayout() = R.layout.fragment_character_details

    override fun getViewModel() = CharacterDetailsViewModel::class

    private var characterId = 0L


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            characterId = CharacterDetailsFragmentArgs.fromBundle(it).characterId
        }
        initObservable()
    }

    private fun initObservable(){
        viewModel.characterLD.observe(viewLifecycleOwner, characterObserver)
    }

    private val characterObserver = Observer<Character?>{
        it?.let {
            character_name_details.text = it.name
            description_value.text = it.description?: "Empty description"
            comic_value.text = it.comics?.items?.size.toString()?: "0"
            stories_value.text = it.stories?.items?.size.toString()?: "0"
            events_value.text = it.events?.items?.size.toString()?: "0"
            series_value.text = it.series?.items?.size.toString()?: "0"
            Glide.with(this)
                .load(it.thumbnail?.getImage())
                .placeholder(R.drawable.ic_android_black_24dp)
                .apply(RequestOptions.overrideOf(600,600))
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(character_photo)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getCharacterById(characterId)
    }
}