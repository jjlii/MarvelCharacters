package com.example.marvelcharacters.ui.list_character

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.Character
import com.example.domain.failure.CharactersFailure
import com.example.domain.failure.Failure
import com.example.marvelcharacters.R
import com.example.marvelcharacters.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_list_character.*

class ListCharacterFragment : BaseFragment<ListCharacterViewModel>() {

    override fun getLayout()= R.layout.fragment_list_character

    override fun getViewModel() = ListCharacterViewModel::class

    private val characterListAdapter = CharactersListAdapter { characterId: Int? ->
        elementClicked(characterId)
    }

    private var characterList = arrayListOf<Character>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservable()

        characterListView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = characterListAdapter
        }

    }

    private fun initObservable(){
        viewModel.charactersListLD.observe(viewLifecycleOwner,characterListObserver)
        viewModel.failureLD.observe(viewLifecycleOwner,failureObserver)
    }

    private val characterListObserver = Observer<List<Character>?>{
        it?.let {
            characterListAdapter.submitList(it)
            toast("Get it")
        }?: run{
            toast("The characters list is empty")
        }
    }

    private val failureObserver = Observer<Failure>{
        when(it){
            Failure.ServerError ->
                toast("ServerError")
            Failure.Unknown ->
                toast("UnknownError")
            is CharactersFailure.ConflictMessage ->
                toast(it.message)
            is CharactersFailure.Unauthorized->
                toast("Unauthorized")
        }
    }

    private fun elementClicked(characterId: Int?){
        toast(characterId.toString())
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllCharacters()
    }




}