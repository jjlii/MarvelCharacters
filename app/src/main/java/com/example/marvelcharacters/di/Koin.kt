package com.example.marvelcharacters.di


import com.example.data.source.CharacterRetrofit
import com.example.data.source.CharacterRetrofitDataSource
import com.example.data.source.Network
import com.example.domain.CharacterDataSource
import com.example.marvelcharacters.ui.character_details.CharacterDetailsViewModel
import com.example.marvelcharacters.ui.list_character.ListCharacterViewModel
import com.example.usecase.GetAllCharacterUseCase
import com.example.usecase.GetCharacterByIdUseCase
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

object Koin{

    val appModule = module{
        viewModel{ ListCharacterViewModel(get())}
        viewModel { CharacterDetailsViewModel(get()) }
    }

    val dataModule = module{
        single<CharacterDataSource>{CharacterRetrofitDataSource(get())}
        single{ Network.initRetrofit().create(CharacterRetrofit::class.java) }
    }

    val usecasesModule = module {
        factory{ GetAllCharacterUseCase(get()) }
        factory { GetCharacterByIdUseCase(get()) }
    }
}