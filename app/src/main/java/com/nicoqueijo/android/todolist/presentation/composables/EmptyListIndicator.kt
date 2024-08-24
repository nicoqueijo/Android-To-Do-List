package com.nicoqueijo.android.todolist.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.nicoqueijo.android.todolist.R
import com.nicoqueijo.android.todolist.presentation.theme.ToDoListTheme
import com.nicoqueijo.android.todolist.presentation.util.DarkLightPreviews
import com.nicoqueijo.android.todolist.presentation.util.L
import com.nicoqueijo.android.todolist.presentation.util.XL
import com.nicoqueijo.android.todolist.presentation.util.XS

/**
 * Composable function for displaying an indicator when a list is empty.
 *
 * This function provides a UI element to indicate that a list is empty, including an icon and
 * instructional text.
 *
 * @param modifier The modifier to be applied to the layout.
 */
@Composable
fun EmptyListIndicator(
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier.padding(horizontal = L, vertical = XL),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(space = XS)
        ) {
            Icon(
                modifier = Modifier.size(size = 125.dp),
                tint = MaterialTheme.colorScheme.primary,
                imageVector = Icons.AutoMirrored.Default.List,
                contentDescription = null
            )
            Text(
                text = stringResource(id = R.string.empty_list_title),
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center
            )
            Text(
                text = stringResource(id = R.string.add_todo_item_instruction),
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center
            )
        }
    }
}

@DarkLightPreviews
@Composable
fun EmptyListIndicatorPreview() {
    ToDoListTheme {
        EmptyListIndicator()
    }
}