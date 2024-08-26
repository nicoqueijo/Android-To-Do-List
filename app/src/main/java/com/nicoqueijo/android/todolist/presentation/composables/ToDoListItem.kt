package com.nicoqueijo.android.todolist.presentation.composables

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
import com.nicoqueijo.android.todolist.domain.model.ToDo
import com.nicoqueijo.android.todolist.presentation.theme.ToDoListTheme
import com.nicoqueijo.android.todolist.presentation.util.DarkLightPreviews
import com.nicoqueijo.android.todolist.presentation.util.XS
import com.nicoqueijo.android.todolist.presentation.util.XXXS
import com.psoffritti.taptargetcompose.TapTargetCoordinator
import com.psoffritti.taptargetcompose.TapTargetScope
import sh.calvin.reorderable.ReorderableCollectionItemScope

/**
 * A composable function representing a single To-Do list item with its interactive elements.
 *
 * @param modifier Modifier to be applied to the outer [Surface] component.
 * @param state The [ToDo] item to be displayed.
 * @param reorderableScope Optional scope for drag-and-drop reordering.
 * @param showTapTargets Boolean to control the visibility of tap targets for guidance.
 * @param onEdit Lambda invoked when the item is edited.
 * @param onDrag Lambda invoked when the item is dragged.
 * @param onCheck Lambda invoked when the checkbox is checked or unchecked.
 * @param onRemove Lambda invoked when the delete icon is clicked.
 */
@Composable
fun TapTargetScope.ToDoListItem(
    modifier: Modifier = Modifier,
    state: ToDo,
    reorderableScope: ReorderableCollectionItemScope? = null,
    showTapTargets: Boolean = false,
    onEdit: (() -> Unit)? = null,
    onDrag: (() -> Unit)? = null,
    onCheck: ((ToDo) -> Unit)? = null,
    onRemove: ((ToDo) -> Unit)? = null,
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
                )
        ) {
            Row(
                modifier = Modifier
                    .background(color = MaterialTheme.colorScheme.surface)
                    .fillMaxWidth(),
            ) {
                Icon(
                    modifier = if (reorderableScope != null) {
                        with(reorderableScope) {
                            Modifier
                                .padding(top = XS)
                                .draggableHandle {
                                    onDrag?.invoke()
                                }
                                .then(
                                    if (showTapTargets) {
                                        Modifier.tapTarget(
                                            tapTargetDefinition = reorderToDoTapTargetDefinition(),
                                        )
                                    } else {
                                        Modifier
                                    }
                                )
                        }
                    } else {
                        Modifier.padding(top = XS)
                    },
                    imageVector = Icons.Filled.Menu,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                )
                Checkbox(
                    modifier = Modifier.then(
                        if (showTapTargets) {
                            Modifier.tapTarget(
                                tapTargetDefinition = completeToDoTapTargetDefinition(),
                            )
                        } else {
                            Modifier
                        }
                    ),
                    checked = state.isCompleted,
                    onCheckedChange = {
                        onCheck?.invoke(state)
                    }
                )

                Column(
                    modifier = Modifier
                        .weight(weight = 1f)
                        .clickable {
                            onEdit?.invoke()
                        }
                ) {
                    Text(
                        modifier = Modifier
                            .padding(top = XS)
                            .then(
                                if (showTapTargets) {
                                    Modifier.tapTarget(
                                        tapTargetDefinition = editToDoTapTargetDefinition(),
                                    )
                                } else {
                                    Modifier
                                }
                            ),
                        text = state.title,
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        textDecoration = if (state.isCompleted) {
                            TextDecoration.LineThrough
                        } else {
                            null
                        },
                    )
                    if (state.description != null) {
                        Spacer(
                            modifier = Modifier.size(size = XXXS)
                        )
                        Text(
                            modifier = Modifier.padding(bottom = XS),
                            text = state.description,
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Normal,
                            textDecoration = if (state.isCompleted) {
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
                            onRemove?.invoke(state)
                        }
                        .then(
                            if (showTapTargets) {
                                Modifier.tapTarget(
                                    tapTargetDefinition = removeToDoTapTargetDefinition(),
                                )
                            } else {
                                Modifier
                            }
                        ),
                    imageVector = Icons.Filled.Delete,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                )
            }

        }
    }
}

@DarkLightPreviews
@Composable
fun ToDoListItemPreview() {
    val state = ToDo(
        position = 1,
        title = "Grocery Shopping",
        description = "Buy groceries for the week, including fruits, vegetables, milk, bread, and eggs.",
    )
    ToDoListTheme {
        TapTargetCoordinator(showTapTargets = false) {
            ToDoListItem(state = state)
        }
    }
}

@DarkLightPreviews
@Composable
fun ToDoListItemCompletedPreview() {
    val state = ToDo(
        position = 1,
        title = "Exercise",
        description = "Go for a 30-minute run in the park and complete a 15-minute stretching session.",
        isCompleted = true,
    )
    ToDoListTheme {
        TapTargetCoordinator(showTapTargets = false) {
            ToDoListItem(state = state)
        }
    }
}
