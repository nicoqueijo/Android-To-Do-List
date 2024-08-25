package com.nicoqueijo.android.todolist.presentation.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nicoqueijo.android.todolist.R
import com.nicoqueijo.android.todolist.domain.ToDoViewModel
import com.nicoqueijo.android.todolist.domain.model.ToDo
import com.nicoqueijo.android.todolist.domain.model.UiEvent
import com.nicoqueijo.android.todolist.presentation.model.UiState
import com.nicoqueijo.android.todolist.presentation.theme.ToDoListTheme
import com.nicoqueijo.android.todolist.presentation.util.DarkLightPreviews
import com.nicoqueijo.android.todolist.presentation.util.S
import com.nicoqueijo.android.todolist.presentation.util.XL
import com.nicoqueijo.android.todolist.presentation.util.XXXS
import com.psoffritti.taptargetcompose.TapTargetCoordinator

@Composable
fun ToDoScreen(
    modifier: Modifier = Modifier,
    viewModel: ToDoViewModel? = hiltViewModel(),
) {
    val uiState = viewModel?.uiState?.collectAsStateWithLifecycle()?.value
    ToDoScreen(
        modifier = modifier,
        state = uiState,
        onEvent = { event ->
            viewModel?.onEvent(event = event)
        }
    )
}

@SuppressLint("MutableCollectionMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToDoScreen(
    modifier: Modifier = Modifier,
    state: UiState?,
    onEvent: ((UiEvent) -> Unit)? = null,
) {
    val snackbarHostState = remember { SnackbarHostState() }
    Surface(
        modifier = modifier.fillMaxSize(),
    ) {
        Scaffold(
            topBar = {
                Column {
                    TopAppBar(
                        navigationIcon = {
                            Row {
                                Spacer(
                                    modifier = Modifier.width(XXXS)
                                )
                                Image(
                                    modifier = Modifier.size(size = XL),
                                    painter = painterResource(id = R.drawable.ic_launcher_foreground), // Replace with an app icon
                                    contentDescription = null
                                )
                            }
                        },
                        title = {
                            Text(
                                modifier = Modifier.padding(start = XXXS),
                                text = stringResource(id = R.string.app_name),
                                color = MaterialTheme.colorScheme.primary,
                            )
                        },
                        actions = {
                            if (state?.toDos?.isNotEmpty() == true) {
                                IconButton(
                                    onClick = {
                                        onEvent?.invoke(UiEvent.DeleteAllToDos)
                                    }
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.Delete,
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.primary,
                                    )
                                }
                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.onSurface
                        )
                    )
                    HorizontalDivider()
                }
            }
        ) { innerPadding ->
            var rememberedToDoStates by remember { mutableStateOf(state?.toDos?.toMutableStateList()) } // Wrapping in a remember is required to enable reordering.
            rememberedToDoStates = state?.toDos?.toMutableStateList() // Assignment allows currencies to show up on the screen.
            Box(
                modifier = Modifier
                    .background(color = MaterialTheme.colorScheme.surface)
                    .padding(paddingValues = innerPadding),
            ) {
                if (state?.showDialog == true) {
                    RemoveToDosDialog(
                        onConfirmClick = {
                            onEvent?.invoke(UiEvent.ConfirmDialog)
                        },
                        onDismissClick = {
                            onEvent?.invoke(UiEvent.CancelDialog)
                        },
                    )
                }
                if (state?.showBottomSheet == true) {
                    ToDoBottomSheetItem(
                        state = state.activeToDo,
                        onSave = { toDo ->
                            onEvent?.invoke(UiEvent.SaveToDo(toDo))  // make activeToDo null
                        },
                        onDismiss = {
                            onEvent?.invoke(UiEvent.DismissBottomSheet) // make activeToDo null
                        },
                    )
                }
                Column {
                    TapTargetCoordinator(
                        modifier = Modifier.fillMaxSize(),
                        showTapTargets = state?.isFirstLaunch == true,
                        onComplete = {
                            onEvent?.invoke(
                                UiEvent.ToggleOffIsFirstLaunch
                            )
                        },
                    ) {
                        Column {
                            Box(
                                contentAlignment = Alignment.BottomCenter
                            ) {
                                if (state?.toDos?.isEmpty() == true) {
                                    EmptyListIndicator()
                                } else {
                                    val lazyListState = rememberLazyListState()
                                    /*val reorderableLazyColumnState =
                                        rememberReorderableLazyListState(lazyListState) { from, to ->
                                            rememberedCurrencies = rememberedCurrencies?.apply {
                                                add(to.index, removeAt(from.index))
                                            }
                                        }*/
                                    LazyColumn(
                                        modifier = Modifier.fillMaxSize(),
                                        /*contentPadding = PaddingValues(vertical = (0.15).dp), // https://github.com/Calvin-LL/Reorderable/issues/32#issuecomment-2099453540*/
                                        state = lazyListState,
                                    ) {
                                        items(
                                            items = rememberedToDoStates?.toList() ?: emptyList(),
                                            key = { toDo -> toDo.hashCode() }
                                        ) { toDo ->
                                            ToDoListItem(
                                                state = toDo,
                                                onEdit = {
                                                    onEvent?.invoke(UiEvent.EditToDo(toDo))
                                                }
                                            )
                                            HorizontalDivider()
                                        }
                                        item {
                                            // Ensures the Floating Action Button (FAB) does not obscure the last item when the list is scrolled to its bottommost position.
                                            Spacer(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .height(88.dp)
                                            )
                                        }
                                    }
                                }
                                Box(
                                    modifier = Modifier
                                        .height(72.dp)
                                        .fillMaxWidth()
                                        .background(
                                            brush = Brush.verticalGradient(
                                                colors = listOf(
                                                    Color.Transparent,
                                                    MaterialTheme.colorScheme.surface,
                                                )
                                            )
                                        )
                                )
                                SnackbarHost(
                                    modifier = Modifier.padding(bottom = 72.dp),
                                    hostState = snackbarHostState,
                                    snackbar = { data ->
                                        Snackbar(
                                            snackbarData = data,
                                            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                                            contentColor = MaterialTheme.colorScheme.secondary,
                                            actionColor = MaterialTheme.colorScheme.tertiary,
                                        )
                                    }
                                )
                                FloatingActionButton(
                                    modifier = Modifier
                                        .padding(bottom = S),
                                    containerColor = MaterialTheme.colorScheme.primary,
                                    contentColor = MaterialTheme.colorScheme.secondary,
                                    onClick = { onEvent?.invoke(UiEvent.AddToDo) },
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.Add,
                                        contentDescription = null,
                                    )
                                }
                            }

                        }
                    }
                }
            }
        }
    }
}

@DarkLightPreviews
@Composable
fun ToDoScreenPreview() {
    val state = UiState(
        toDos = listOf(
            ToDo(
                position = 1,
                title = "Grocery Shopping",
                description = "Buy groceries for the week, including fruits, vegetables, milk, bread, and eggs.",
            ),
            ToDo(
                position = 2,
                title = "Plan Weekend Trip",
                description = "Research destinations for a weekend trip, book a hotel, and plan activities.",
            ),
            ToDo(
                position = 3,
                title = "Morning Exercise",
                description = "Go for a 30-minute run in the park and complete a 15-minute stretching session.",
                isCompleted = true,
            ),
            ToDo(
                position = 4,
                title = "Call Mom",
                description = "Check in with Mom about the weekend plans and how she’s doing.",
            ),
            ToDo(
                position = 5,
                title = "Prepare Presentation",
                description = "Create slides and practice the presentation for Monday’s team meeting.",
            ),
            ToDo(
                position = 6,
                title = "Visit the Bank",
                description = "Deposit the paycheck and inquire about opening a new savings account.",
                isCompleted = true,
            ),
            ToDo(
                position = 7,
                title = "Organize Desk",
                description = "Sort through paperwork, declutter, and organize the desk drawers.",
                isCompleted = true,
            ),
            ToDo(
                position = 8,
                title = "Call Mom",
                description = "Check in with Mom about the weekend plans and how she’s doing.",
                isCompleted = true,
            ),
        )
    )
    ToDoListTheme {
        ToDoScreen(state = state)
    }
}

@DarkLightPreviews
@Composable
fun ToDoScreenDialogPreview() {
    val state = UiState(
        toDos = listOf(
            ToDo(
                position = 1,
                title = "Grocery Shopping",
                description = "Buy groceries for the week, including fruits, vegetables, milk, bread, and eggs.",
            ),
            ToDo(
                position = 2,
                title = "Plan Weekend Trip",
                description = "Research destinations for a weekend trip, book a hotel, and plan activities.",
            ),
            ToDo(
                position = 3,
                title = "Morning Exercise",
                description = "Go for a 30-minute run in the park and complete a 15-minute stretching session.",
                isCompleted = true,
            ),
        ),
        showDialog = true,
    )
    ToDoListTheme {
        ToDoScreen(state = state)
    }
}

@DarkLightPreviews
@Composable
fun ToDoScreenEmptyPreview() {
    val state = UiState()
    ToDoListTheme {
        ToDoScreen(state = state)
    }
}