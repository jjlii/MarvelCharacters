package com.example.marvelcharacters.data

import android.util.Log
import com.example.data.source.CharacterDataSource
import com.example.domain.Character
import com.example.domain.Constant
import com.example.domain.Constant.ConnectionUtils.PUB_KEY
import com.example.domain.Constant.ConnectionUtils.PV_KEY
import com.example.domain.Either
import com.example.domain.failure.CharactersFailure
import com.example.domain.failure.Failure
import retrofit2.Response
import java.security.MessageDigest
import java.util.*

class CharacterRetrofitDataSource(private val characterRetrofit: CharacterRetrofit): CharacterDataSource {

    override suspend fun getAllCharacters(): Either<Failure, List<Character>?> {
        return try {
            val ts = Date().time
            val hash = getHash("${ts}${PV_KEY}${PUB_KEY}")
            val requestResp: Response<CharacterResp> =
                characterRetrofit.getAllCharacters(ts = ts, hash = hash,apikey = PUB_KEY)

            when (requestResp.code()){
                200 -> {
                    val characterResp = requestResp.body()
                    Either.Sucess(characterResp?.data?.results)
                }
                409 ->
                    Either.Failure(CharactersFailure.ConflictMessage(requestResp.message()))
                401->
                    Either.Failure(CharactersFailure.Unauthorized)
                else ->
                    Either.Failure(Failure.ServerError)
            }
        }catch (e : Exception){
            Log.e("getAllCharacters ->", e.message.toString())
            Either.Failure(Failure.Unknown)
        }
    }


    override suspend fun getCharacterById(characterId: Long): Either<Failure, Character?> {
        return try {
            val ts = Date().time
            val hash = getHash("${ts}${PV_KEY}${PUB_KEY}")
            val requestResp: Response<CharacterResp> = characterRetrofit.getCharacterById(
                characterId =  characterId, ts = ts, hash = hash,apikey = PUB_KEY)

            when(requestResp.code()){
                200->{
                    val characterResp = requestResp.body()
                    Either.Sucess(characterResp?.data?.results?.get(0))
                }
                404->
                    Either.Failure(CharactersFailure.NotFound)
                401->
                    Either.Failure(CharactersFailure.Unauthorized)
                else ->
                    Either.Failure(Failure.ServerError)
            }
        }catch (e : Exception){
            Log.e("getCharacterById ->", e.message.toString())
            Either.Failure(Failure.Unknown)
        }
    }

    private fun getHash(str : String) : String{
        val bytes = MessageDigest.getInstance("MD5").digest(str.toByteArray())
        return bytes.joinToString(""){
            "%02x".format(it)
        }
    }

}