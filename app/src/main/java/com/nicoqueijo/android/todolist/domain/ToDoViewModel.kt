package com.nicoqueijo.android.todolist.domain

import androidx.lifecycle.ViewModel
import com.nicoqueijo.android.todolist.core.di.DefaultDispatcher
import com.nicoqueijo.android.todolist.presentation.model.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ToDoViewModel @Inject constructor(
    /*private val useCases: ToDoUseCases,*/
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher,
) : ViewModel() {

    private val _uiState = MutableStateFlow(value = UiState())
    val uiState = _uiState.asStateFlow()

}