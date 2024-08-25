package com.nicoqueijo.android.todolist.domain.usecases

import com.nicoqueijo.android.todolist.data.Repository
import javax.inject.Inject

class DeleteAllToDosUseCase @Inject constructor(
    private val repository: Repository,
) {
    suspend operator fun invoke() {
        repository.deleteAllToDos()
    }
}
