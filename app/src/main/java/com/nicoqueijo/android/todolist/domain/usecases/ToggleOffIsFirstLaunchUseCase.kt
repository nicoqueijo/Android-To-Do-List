package com.nicoqueijo.android.todolist.domain.usecases

import com.nicoqueijo.android.todolist.data.Repository
import javax.inject.Inject

/**
 * Use case to toggle off the flag indicating the first launch of the application. It updates the repository
 * to mark the first launch as completed.
 *
 * @property repository The repository interface for data operations.
 */
class ToggleOffIsFirstLaunchUseCase @Inject constructor(
    private val repository: Repository,
) {

    /**
     * Toggles off the first launch flag in the repository.
     */
    suspend operator fun invoke() {
        repository.setFirstLaunch(value = false)
    }
}