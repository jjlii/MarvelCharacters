package com.example.marvelcharacters

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.domain.CharacterDataSource
import com.example.domain.Either
import com.example.domain.entity.Character
import com.example.domain.failure.Failure
import com.example.domain.test.mockedCharacter
import com.example.marvelcharacters.ui.list_character.ListCharacterViewModel
import com.example.usecase.GetAllCharacterUseCase
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
class ListCharacterViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var getAllCharacterUseCase: GetAllCharacterUseCase

    @Mock
    lateinit var listCharacterObserver: Observer<List<Character>?>

    @Mock
    lateinit var characterDataSource: CharacterDataSource

    @Mock
    lateinit var failureObserver: Observer<Failure>

    @Mock
    lateinit var loadingObserver: Observer<Boolean>


    @Captor
    lateinit var intCaptor: ArgumentCaptor<Int>

    private lateinit var listCharacterViewModel: ListCharacterViewModel

    @Before
    fun setUp() {
        listCharacterViewModel = ListCharacterViewModel(getAllCharacterUseCase)
    }

    @Test
    fun `when calls getAllCharacters should call getAllCharacterUseCase and notify observer with success`() {
        runBlocking {
            val offset: Int = 0
            val characters:List<Character>? = listOf(mockedCharacter)

            whenever(getAllCharacterUseCase.run(intCaptor.capture())).thenReturn(Either.Sucess(characters))
            listCharacterViewModel.charactersListLD.observeForever(listCharacterObserver)
            listCharacterViewModel.loadingLD.observeForever(loadingObserver)
            listCharacterViewModel.getAllCharacters(offset)

            verify(loadingObserver).onChanged(true)
            verify(listCharacterObserver).onChanged(characters)
            assertEquals(intCaptor.value, offset)
        }
    }

    @Test
    fun `when calls getAllCharacters should call getAllCharacterUseCase and notify observer with failure`() {
        runBlocking {
            val offset: Int = 0
            val expResult = Failure.Unknown("")

            whenever(getAllCharacterUseCase.run(offset)).thenReturn(Either.Failure(Failure.Unknown("")))
            listCharacterViewModel.failureLD.observeForever(failureObserver)
            listCharacterViewModel.loadingLD.observeForever(loadingObserver)
            listCharacterViewModel.getAllCharacters(offset)

            verify(loadingObserver).onChanged(true)
            verify(failureObserver).onChanged(expResult)
            assertEquals(intCaptor.value, offset)
        }
    }
}