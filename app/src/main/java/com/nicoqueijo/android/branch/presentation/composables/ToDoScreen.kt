package com.nicoqueijo.android.branch.presentation.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.nicoqueijo.android.branch.presentation.model.ScreenState
import com.nicoqueijo.android.branch.presentation.theme.BranchTheme
import com.nicoqueijo.android.branch.presentation.util.DarkLightPreviews

@Composable
fun ToDoScreen(
    modifier: Modifier = Modifier,
    /*viewModel: ToDoViewModel? = hiltViewModel(),*/
) {

}

@Composable
fun ToDoScreen(
    modifier: Modifier = Modifier,
    state: ScreenState,
) {

}

@DarkLightPreviews
@Composable
fun ToDoScreenPreview() {
    val state = ScreenState(
        sd = 2
    )
    BranchTheme {
        ToDoScreen(state = state)
    }
}