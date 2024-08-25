package com.nicoqueijo.android.todolist.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.sp
import com.nicoqueijo.android.todolist.R
import com.nicoqueijo.android.todolist.domain.model.ToDo
import com.nicoqueijo.android.todolist.presentation.theme.ToDoListTheme
import com.nicoqueijo.android.todolist.presentation.util.DarkLightPreviews
import com.nicoqueijo.android.todolist.presentation.util.S
import com.nicoqueijo.android.todolist.presentation.util.XXS

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToDoBottomSheetItem(
    modifier: Modifier = Modifier,
    sheetState: SheetState = rememberModalBottomSheetState(),
    state: ToDo?,
    onSave: ((ToDo) -> Unit)? = null,
    onDismiss: (() -> Unit)? = null,
) {
    ModalBottomSheet(
        modifier = modifier,
        sheetState = sheetState,
        onDismissRequest = { onDismiss?.invoke() },
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        contentColor = MaterialTheme.colorScheme.surfaceVariant
    ) {
        SheetContent(
            state = state,
            onSave = onSave,
        )
    }
}

@Composable
fun SheetContent(
    modifier: Modifier = Modifier,
    state: ToDo?,
    onSave: ((ToDo) -> Unit)? = null,
) {
    val title = remember {
        mutableStateOf(
            TextFieldValue(
                state?.title ?: "",
                TextRange(state?.title?.length ?: 0)
            )
        )
    }
    val description = remember { mutableStateOf(state?.description ?: "") }
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
    Column(
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.surfaceVariant)
            .fillMaxWidth()
            .padding(S)
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = XXS)
                .focusRequester(focusRequester),
            value = title.value,
            onValueChange = { newText ->
                title.value = newText
            },
            label = {
                Text(
                    text = stringResource(R.string.label_title)
                )
            },
            colors = TextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.primary,
                unfocusedTextColor = MaterialTheme.colorScheme.primary,
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            )
        )

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = XXS),
            value = description.value,
            onValueChange = { newText ->
                description.value = newText
            },
            label = {
                Text(
                    text = stringResource(R.string.label_description)
                )
            },
            colors = TextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.primary,
                unfocusedTextColor = MaterialTheme.colorScheme.primary,
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            ),
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = S),
            contentAlignment = Alignment.BottomEnd
        ) {
            Button(
                onClick = {
                    val updatedToDo = state?.copy(
                        title = title.value.text,
                        description = description.value
                    ) ?: ToDo(
                        title = title.value.text,
                        description = description.value
                    )
                    onSave?.invoke(updatedToDo)
                },
                enabled = title.value.text.isNotBlank(),
                colors = ButtonDefaults.buttonColors(
                    disabledContainerColor = MaterialTheme.colorScheme.tertiary,
                    disabledContentColor = MaterialTheme.colorScheme.tertiaryContainer.copy(
                        alpha = 0.5f
                    )
                )
            ) {
                Text(
                    text = stringResource(R.string.label_save),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@DarkLightPreviews
@Composable
fun SheetContentPreview() {
    ToDoListTheme {
        SheetContent(
            state = ToDo(
                position = 1,
                title = "Exercise",
                description = "Go for a 30-minute run in the park and complete a 15-minute stretching session.",
            )
        )
    }
}

@DarkLightPreviews
@Composable
fun SheetContentEmptyPreview() {
    ToDoListTheme {
        SheetContent(
            state = ToDo(
                position = 1,
                title = "",
                description = "",
            )
        )
    }
}