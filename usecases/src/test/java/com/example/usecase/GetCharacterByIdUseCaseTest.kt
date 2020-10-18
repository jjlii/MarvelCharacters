package com.example.usecase

import com.example.domain.CharacterDataSource
import com.example.domain.Either
import com.example.domain.failure.Failure
import com.example.domain.test.mockedCharacter
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetCharacterByIdUseCaseTest{

    @Mock
    lateinit var characterDataSource: CharacterDataSource

    @Captor
    lateinit var logCaptor: ArgumentCaptor<Long>

    private lateinit var getCharacterByIdUseCase: GetCharacterByIdUseCase

    @Before
    fun setUp() {
        getCharacterByIdUseCase = GetCharacterByIdUseCase(characterDataSource)
    }

    @Test
    fun `when calls GetCharacterByIdUseCase should passed characterId and return success`(){
        runBlocking {
            val expResult = Either.Sucess(mockedCharacter)
            val characterId = 1L

            whenever(characterDataSource.getCharacterById(logCaptor.capture())).thenReturn(expResult)

            val result = getCharacterByIdUseCase.run(characterId)

            assertEquals(expResult, result)
            assertEquals(characterId, logCaptor.value)
        }
    }

    @Test
    fun `when calls GetCharacterByIdUseCase should return failure`(){
        runBlocking {
            val expResult = Either.Failure(Failure.Unknown(""))

            whenever(characterDataSource.getCharacterById(any())).thenReturn(expResult)

            val result = getCharacterByIdUseCase.run(any())

            assertEquals(expResult, result)
        }
    }

}