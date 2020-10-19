package com.example.marvelcharacters.ui.character_details

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.domain.entity.Character
import com.example.domain.failure.CharactersFailure
import com.example.domain.failure.Failure
import com.example.marvelcharacters.R
import com.example.marvelcharacters.getImage
import com.example.marvelcharacters.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_character_details.*
import org.koin.android.viewmodel.ext.android.viewModel

class CharacterDetailsFragment : BaseFragment<CharacterDetailsViewModel>() {

    override fun getLayout() = R.layout.fragment_character_details

    override val viewModel: CharacterDetailsViewModel by viewModel(clazz = CharacterDetailsViewModel::class)


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
        viewModel.failureLD.observe(viewLifecycleOwner, failureObserver)
    }

    private val characterObserver = Observer<Character?>{
        it?.let {
            character_name_details.text = it.name
            description_value.text = it.description?: "Empty description"
            comic_value.text = it.comics?.items?.size.toString()
            stories_value.text = it.stories?.items?.size.toString()
            events_value.text = it.events?.items?.size.toString()
            series_value.text = it.series?.items?.size.toString()
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

    private val failureObserver = Observer<Failure>{
        when(it){
            is Failure.ServerError ->
                toast("ServerError with error code ${it.message}")
            is Failure.Unknown ->{
                toast("UnknownError")
                Log.e("UnknownError", it.message)
            }
            is CharactersFailure.NotFound->
                toast("Data not found")
            is CharactersFailure.Unauthorized->
                toast("Unauthorized")
        }
    }

}