package com.example.marvelcharacters.di


import com.example.data.source.CharacterRetrofit
import com.example.data.source.CharacterRetrofitDataSource
import com.example.data.source.Network
import com.example.domain.CharacterDataSource
import com.example.marvelcharacters.ui.character_details.CharacterDetailsViewModel
import com.example.marvelcharacters.ui.list_character.ListCharacterViewModel
import com.example.usecase.GetAllCharacterUseCase
import com.example.usecase.GetCharacterByIdUseCase
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

object Koin{

    val appModule = module{
        viewModel{(useCase: GetAllCharacterUseCase)-> ListCharacterViewModel(useCase)}
        viewModel{ (useCase: GetCharacterByIdUseCase)->CharacterDetailsViewModel(useCase) }
    }

    val dataModule = module{
        single<CharacterDataSource>{CharacterRetrofitDataSource(get())}
        single{ Network.initRetrofit("https://gateway.marvel.com/v1/public/").create(CharacterRetrofit::class.java) }
    }

    val useCaseModel = module {
        factory { GetAllCharacterUseCase(get())  }
        factory { GetCharacterByIdUseCase(get()) }
    }
}