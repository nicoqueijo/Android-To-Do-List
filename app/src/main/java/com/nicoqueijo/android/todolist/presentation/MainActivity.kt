package com.nicoqueijo.android.todolist.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.nicoqueijo.android.todolist.data.Repository
import com.nicoqueijo.android.todolist.presentation.composables.ToDoScreen
import com.nicoqueijo.android.todolist.presentation.theme.ToDoListTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var repo: Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ToDoListTheme {
                ToDoScreen()
            }
        }
    }
}
