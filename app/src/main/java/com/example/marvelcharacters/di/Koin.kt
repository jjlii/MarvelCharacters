package com.example.marvelcharacters.di

import com.example.data.source.CharacterDataSource
import com.example.marvelcharacters.data.CharacterRetrofit
import com.example.marvelcharacters.data.CharacterRetrofitDataSource
import com.example.marvelcharacters.data.Network
import com.example.marvelcharacters.ui.character_details.CharacterDetailsViewModel
import com.example.marvelcharacters.ui.list_character.ListCharacterViewModel
import com.example.usecase.GetAllCharacterUseCase
import com.example.usecase.GetCharacterByIdUseCase
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.experimental.builder.viewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

object Koin{

    val appModule = module{
        single{ Network.initRetrofit().create(CharacterRetrofit::class.java) }
        viewModel{ ListCharacterViewModel(get())}
        viewModel { CharacterDetailsViewModel(get()) }
    }

    val dataModule = module{
        single<CharacterDataSource>{ CharacterRetrofitDataSource(get())}
    }

    val usecasesModule = module {
        factory{ GetAllCharacterUseCase(get()) }
        factory { GetCharacterByIdUseCase(get()) }
    }
}