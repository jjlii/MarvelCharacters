package com.example.marvelcharacters.di

import com.example.marvelcharacters.ui.list_character.ListCharacterViewModel
import com.example.usecase.GetAllCharacterUseCase
import com.example.usecase.GetCharacterByIdUseCase
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.experimental.builder.viewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

class Koin{

    val appModule = module{
        viewModel{ ListCharacterViewModel(androidApplication(), get())}
    }

    val dataModule = module {

    }

    val usecasesModule = module {
        factory{ GetAllCharacterUseCase(get()) }
        factory { GetCharacterByIdUseCase(get()) }
    }
}