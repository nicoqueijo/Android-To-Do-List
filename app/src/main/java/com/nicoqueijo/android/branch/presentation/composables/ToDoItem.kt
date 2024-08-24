package com.nicoqueijo.android.branch.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.nicoqueijo.android.branch.domain.model.ToDo
import com.nicoqueijo.android.branch.presentation.model.ToDoItemState
import com.nicoqueijo.android.branch.presentation.theme.BranchTheme
import com.nicoqueijo.android.branch.presentation.util.DarkLightPreviews
import com.nicoqueijo.android.branch.presentation.util.XS
import com.nicoqueijo.android.branch.presentation.util.XXXS

@Composable
fun ToDoItem(
    modifier: Modifier = Modifier,
    state: ToDoItemState,
) {
    Surface(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.surface)
                .padding(
                    start = XS,
                    end = XS,
                    bottom = XS,
                )
        ) {
            Row(
                modifier = Modifier
                    .background(color = MaterialTheme.colorScheme.surface)
                    .fillMaxWidth(),
            ) {
                Icon(
                    modifier = Modifier.padding(top = XS),
                    imageVector = Icons.Default.Menu,
                    contentDescription = null,
                )
                Checkbox(
                    checked = state.toDo.isCompleted,
                    onCheckedChange = { isChecked ->
                        state.onCheck?.invoke(isChecked)
                    }
                )

                Column(
                    modifier = Modifier.weight(weight = 1f)
                ) {
                    Text(
                        modifier = Modifier
                            .padding(top = XS)
                            .clickable {
                                state.onEdit?.invoke(state.toDo)
                            },
                        text = state.toDo.title,
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        textDecoration = if (state.toDo.isCompleted) {
                            TextDecoration.LineThrough
                        } else {
                            null
                        },
                    )
                    if (state.toDo.description != null) {
                        Spacer(
                            modifier = Modifier.size(size = XXXS)
                        )
                        Text(
                            modifier = Modifier.clickable {
                                state.onEdit?.invoke(state.toDo)
                            },
                            text = state.toDo.description,
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Normal,
                            textDecoration = if (state.toDo.isCompleted) {
                                TextDecoration.LineThrough
                            } else {
                                null
                            },
                        )
                    }
                }
                Icon(
                    modifier = Modifier
                        .padding(top = XS)
                        .clickable {
                            state.onRemove?.invoke(state.toDo)
                        },
                    imageVector = Icons.Default.Delete,
                    contentDescription = null,
                )
            }

        }
    }
}

@DarkLightPreviews
@Composable
fun ToDoItemPreview() {
    val state = ToDoItemState(
        toDo = ToDo(
            position = 1,
            title = "Grocery Shopping",
            description = "Buy groceries for the week, including fruits, vegetables, milk, bread, and eggs.",
        )
    )
    BranchTheme {
        ToDoItem(state = state)
    }
}

@DarkLightPreviews
@Composable
fun ToDoCompletedPreview() {
    val state = ToDoItemState(
        toDo = ToDo(
            position = 1,
            title = "Exercise",
            description = "Go for a 30-minute run in the park and complete a 15-minute stretching session.",
            isCompleted = true,
        )
    )
    BranchTheme {
        ToDoItem(state = state)
    }
}
