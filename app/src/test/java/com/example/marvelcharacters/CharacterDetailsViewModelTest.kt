package com.example.marvelcharacters

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.domain.Either
import com.example.domain.entity.Character
import com.example.domain.failure.CharactersFailure
import com.example.domain.failure.Failure
import com.example.domain.test.mockedCharacter
import com.example.marvelcharacters.ui.character_details.CharacterDetailsViewModel
import com.example.usecase.GetCharacterByIdUseCase
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CharacterDetailsViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var getCharacterByIdUseCase: GetCharacterByIdUseCase

    @Mock
    lateinit var characterObserver: Observer<Character?>

    @Mock
    lateinit var failureObserver: Observer<Failure>

    @Mock
    lateinit var loadingObserver: Observer<Boolean>

    @Captor
    lateinit var longCaptor: ArgumentCaptor<Long>

    private lateinit var characterDetailsViewModel: CharacterDetailsViewModel

    @Before
    fun setUp() {
        characterDetailsViewModel = CharacterDetailsViewModel(getCharacterByIdUseCase)
    }

    @Test
    fun `when calls getCharacterById should call getCharacterByIdUseCase and notify observer with success`() {
        runBlocking {
            val characterId= 1L

            whenever(getCharacterByIdUseCase.invoke(longCaptor.capture())).thenReturn(Either.Sucess(
                mockedCharacter))
            characterDetailsViewModel.characterLD.observeForever(characterObserver)
            characterDetailsViewModel.loadingLD.observeForever(loadingObserver)

            characterDetailsViewModel.getCharacterById(characterId)

            verify(loadingObserver).onChanged(true)
            verify(characterObserver).onChanged(mockedCharacter)
            assertEquals(longCaptor.value, characterId)
        }
    }

    @Test
    fun `when calls getCharacterById should call getCharacterByIdUseCase and notify observer with failure`(){
        runBlocking {
            val characterId = 1L
            val expResult = CharactersFailure.Unauthorized

            whenever(getCharacterByIdUseCase.invoke(longCaptor.capture())).thenReturn(Either.Failure(CharactersFailure.Unauthorized))
            characterDetailsViewModel.failureLD.observeForever(failureObserver)
            characterDetailsViewModel.loadingLD.observeForever(loadingObserver)

            characterDetailsViewModel.getCharacterById(characterId)

            verify(loadingObserver).onChanged(true)
            verify(failureObserver).onChanged(expResult)
            assertEquals(longCaptor.value, characterId)
        }
    }
}