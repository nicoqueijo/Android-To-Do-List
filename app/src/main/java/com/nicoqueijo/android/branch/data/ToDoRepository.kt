package com.nicoqueijo.android.branch.data

import com.nicoqueijo.android.branch.core.di.IODispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class ToDoRepository @Inject constructor(
    private val toDoDao: ToDoDao,
    @IODispatcher private val dispatcher: CoroutineDispatcher,
) : Repository {
}