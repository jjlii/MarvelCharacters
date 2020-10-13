package com.example.marvelcharacters.data

import com.example.data.source.CharacterDataSource
import com.example.domain.Character
import com.example.domain.Either
import com.example.domain.failure.Failure
import retrofit2.Response

class CharacterDataSourceImp(private val characterRetrofit: CharacterRetrofit): CharacterDataSource {

    override suspend fun getAllCharacters(): Either<Failure, List<Character>?> {
        return try {
            val requestResp: Response<CharacterResp> = characterRetrofit.getAllCharacters()

            when (requestResp.code()){
                200 -> {
                    val characterResp = requestResp.body()
                    Either.Sucess(characterResp?.data)
                }
                409 ->
                    Either.Failure(Failure.Conflict)
                else ->
                    Either.Failure(Failure.ServerError)
            }
        }catch (e : Exception){
            Either.Failure(Failure.Unknown)
        }
    }


    override suspend fun getCharacterById(characterId: Long): Either<Failure, Character?> {
        return try {
            val requestResp: Response<CharacterResp> = characterRetrofit.getCharacterById(characterId)

            when(requestResp.code()){
                200->{
                    val characterResp = requestResp.body()
                    Either.Sucess(characterResp?.data?.get(0))
                }
                404->
                    Either.Failure(Failure.NotFound)
                else ->
                    Either.Failure(Failure.ServerError)
            }
        }catch (e : Exception){
            Either.Failure(Failure.Unknown)
        }
    }

}