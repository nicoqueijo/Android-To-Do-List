package com.nicoqueijo.android.branch.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.nicoqueijo.android.branch.data.Repository
import com.nicoqueijo.android.branch.domain.model.ToDo
import com.nicoqueijo.android.branch.presentation.theme.BranchTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var repo: Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BranchTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->

                }
            }
        }

        val todo1 = ToDo(
            position = 1,
            title = "First Title",
            description = "First Description",
        )
        val todo2 = ToDo(
            position = 2,
            title = "Second Title",
            description = "Second Description",
        )
        val todo3 = ToDo(
            position = 3,
            title = "Third Title",
            description = "Third Description",
        )

        runBlocking {
            repo.deleteAllToDos()
            repo.upsertToDo(todo1)
            repo.upsertToDo(todo2)
            repo.upsertToDo(todo3)

            val toDos = repo.getAllToDos()

            repo.toString()
        }
    }
}
