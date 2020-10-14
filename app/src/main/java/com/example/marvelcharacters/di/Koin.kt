package com.example.marvelcharacters.di

import com.example.usecase.GetAllCharacterUseCase
import com.example.usecase.GetCharacterByIdUseCase
import org.koin.dsl.module.module

class Koin{

    val appModule = module{

    }

    val dataModule = module {

    }

    val usecasesModule = module {
        factory{ GetAllCharacterUseCase(get()) }
        factory { GetCharacterByIdUseCase(get()) }
    }
}