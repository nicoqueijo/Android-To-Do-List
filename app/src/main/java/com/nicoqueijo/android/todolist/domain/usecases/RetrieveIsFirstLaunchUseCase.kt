package com.nicoqueijo.android.todolist.domain.usecases

import com.nicoqueijo.android.todolist.data.Repository
import javax.inject.Inject

/**
 * Use case for retrieving the "is first launch" flag.
 *
 * @property repository The [Repository] instance used to access and modify To-Do data.
 */
class RetrieveIsFirstLaunchUseCase @Inject constructor(
    private val repository: Repository,
) {

    /**
     * Retrieves the "is first launch" flag, which indicates whether the application is being launched for the first time.
     *
     * @return `true` if it is the first launch, `false` otherwise.
     */
    suspend operator fun invoke(): Boolean {
        return repository.isFirstLaunch()
    }
}
