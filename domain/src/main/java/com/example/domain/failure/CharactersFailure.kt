package com.example.domain.failure

sealed class CharactersFailure : Failure.FeatureFailure(){


    class ConflictMessage(val message: String): CharactersFailure()
    object NotFound: CharactersFailure()

}