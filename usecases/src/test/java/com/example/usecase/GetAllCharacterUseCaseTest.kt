package com.example.usecase

import com.example.domain.CharacterDataSource
import com.example.domain.Either
import com.example.domain.failure.Failure
import com.example.domain.test.mockedCharacter
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetAllCharacterUseCaseTest{

    @Mock
    lateinit var characterDataSource: CharacterDataSource

    @Captor
    lateinit var intCaptor : ArgumentCaptor<Int>

    private lateinit var getAllCharacterUseCase: GetAllCharacterUseCase

    @Before
    fun setUp() {
        getAllCharacterUseCase = GetAllCharacterUseCase(characterDataSource)
    }

    @Test
    fun `when calls GetAllCharacterUseCase should passed the offset and return success`() {
        runBlocking {
            val offset: Int = 0
            val characters = listOf(mockedCharacter)
            val expResult = Either.Sucess(characters)

            whenever(characterDataSource.getAllCharacters(intCaptor.capture())).thenReturn(expResult)

            val result = getAllCharacterUseCase.invoke(offset)


            assertEquals(intCaptor.value, offset)
            assertEquals(expResult, result)
        }
    }

    @Test
    fun `when calls GetAllCharacterUseCase should return failure`() {
        runBlocking {
            val expResult = Either.Failure(Failure.Unknown(""))

            whenever(characterDataSource.getAllCharacters(any())).thenReturn(expResult)

            val result = getAllCharacterUseCase.invoke(any())



            assertEquals(expResult, result)
        }
    }


}