package com.example.data.source

import com.example.domain.Either
import com.example.domain.test.mockedCharacter
import com.example.domain.test.mockedCharacterRespSuccess
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class CharacterRetrofitDataSourceTest{

    @Mock
    lateinit var characterRetrofit: CharacterRetrofit

    @Captor
    lateinit var intCaptor: ArgumentCaptor<Int>

    @Captor
    lateinit var longCaptor: ArgumentCaptor<Long>

    private lateinit var characterRetrofitDataSource: CharacterRetrofitDataSource

    @Before
    fun setUp() {
        characterRetrofitDataSource = CharacterRetrofitDataSource(characterRetrofit)
    }

    @Test
    fun `when call getAllCharacters should pass offset and return success`() {
        runBlocking{
            val offset  = 0
            val expResult = Either.Sucess(arrayListOf(mockedCharacter))

            whenever(characterRetrofit.getAllCharacters(any(), any(),any(),intCaptor.capture(), any()))
                .thenReturn(Response.success(mockedCharacterRespSuccess))

            val result = characterRetrofitDataSource.getAllCharacters(offset)

            assertEquals(intCaptor.value, offset)
            assertEquals(expResult, result)
            assertTrue(result is Either.Sucess)
        }
    }
    @Test
    fun `when call getAllCharacters should return failure`() {
        runBlocking{
            val offset  = 0

            whenever(characterRetrofit.getAllCharacters(any(), any(),any(), any(), any()))
                .thenReturn(Response.error(409, ResponseBody.create(null, "")))

            val result = characterRetrofitDataSource.getAllCharacters(offset)

            assertTrue(result is Either.Failure)
        }
    }

    @Test
    fun `when calls getCharacterById should pass characterId and return success`() {
        runBlocking {
            val characterId = 1L
            val expResult = Either.Sucess(mockedCharacter)

            whenever(characterRetrofit.getCharacterById(longCaptor.capture(), any(), any(), any())).thenReturn(
                Response.success(mockedCharacterRespSuccess)
            )

            val result = characterRetrofitDataSource.getCharacterById(characterId)

            assertEquals(longCaptor.value,characterId)
            assertEquals(expResult, result)
        }
    }

    @Test
    fun `when call getCharacterById should return failure`(){
        runBlocking {
            val characterId = 1L

            whenever(characterRetrofit.getCharacterById(longCaptor.capture(), any(), any(), any()))
                .thenReturn(Response.error(404, ResponseBody.create(null, "")))

            val result = characterRetrofitDataSource.getCharacterById(characterId)

            assertTrue(result is Either.Failure)
        }
    }


}