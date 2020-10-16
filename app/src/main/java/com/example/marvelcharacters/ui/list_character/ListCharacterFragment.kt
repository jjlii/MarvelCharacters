package com.example.marvelcharacters.ui.list_character

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
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

    private val characterListAdapter = CharactersListAdapter { characterId: Int ->
        elementClicked(characterId)
    }

    private var characterList = arrayListOf<Character>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getAllCharacters()
    }


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
    }

    private val characterListObserver = Observer<List<Character>?>{
        it?.let {
            characterListAdapter.submitList(it)
        }?: run{
            toast("The characters list is empty")
        }
    }



    private fun elementClicked(characterId: Int){
        findNavController().navigate(ListCharacterFragmentDirections.actionListCharacterFragmentToCharacterDetailsFragment(characterId.toLong()))
    }




}