package com.nicoqueijo.android.todolist.presentation.composables

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.nicoqueijo.android.todolist.R
import com.psoffritti.taptargetcompose.TapTargetDefinition
import com.psoffritti.taptargetcompose.TapTargetStyle
import com.psoffritti.taptargetcompose.TextDefinition

/**
 * Composable function to define a tap target for adding a to-do.
 *
 * This function builds and returns a [TapTargetDefinition] for the "add to-do" action,
 * including the title and description.
 *
 * @return The tap target definition for adding a to-do.
 */
@Composable
fun addToDoTapTargetDefinition(): TapTargetDefinition {
    return buildTapTargetDefinition(
        precedence = 0,
        title = stringResource(R.string.tap_target_add_title),
        description = "",
    )
}

/**
 * Composable function to define a tap target for reordering to-dos.
 *
 * This function builds and returns a [TapTargetDefinition] for the "reorder to-do" action,
 * including the title and description.
 *
 * @return The tap target definition for reordering to-dos.
 */
@Composable
fun reorderToDoTapTargetDefinition(): TapTargetDefinition {
    return buildTapTargetDefinition(
        precedence = 1,
        title = stringResource(R.string.tap_target_drag_title),
        description = "",
    )
}

/**
 * Composable function to define a tap target for completing to-dos.
 *
 * This function builds and returns a [TapTargetDefinition] for the "complete to-do" action,
 * including the title and description.
 *
 * @return The tap target definition for completing to-dos.
 */
@Composable
fun completeToDoTapTargetDefinition(): TapTargetDefinition {
    return buildTapTargetDefinition(
        precedence = 2,
        title = stringResource(R.string.tap_target_complete_title),
        description = "",
    )
}

/**
 * Composable function to define a tap target for editing a to-do.
 *
 * This function builds and returns a [TapTargetDefinition] for the "edit to-do" action,
 * including the title and description.
 *
 * @return The tap target definition for editing a to-do.
 */
@Composable
fun editToDoTapTargetDefinition(): TapTargetDefinition {
    return buildTapTargetDefinition(
        precedence = 3,
        title = stringResource(R.string.tap_target_edit_title),
        description = "",
    )
}

/**
 * Composable function to define a tap target for removing a to-do.
 *
 * This function builds and returns a [TapTargetDefinition] for the "remove to-do" action,
 * including the title and description.
 *
 * @return The tap target definition for removing a to-do.
 */
@Composable
fun removeToDoTapTargetDefinition(): TapTargetDefinition {
    return buildTapTargetDefinition(
        precedence = 4,
        title = stringResource(R.string.tap_target_remove_title),
        description = "",
    )
}

/**
 * Composable function to build a tap target definition.
 *
 * This function constructs a [TapTargetDefinition] with the specified precedence, title, and description.
 * It is used by the other tap target definition functions to create consistent definitions.
 *
 * @param precedence The precedence of the tap target.
 * @param title The title text for the tap target.
 * @param description The description text for the tap target.
 * @return The constructed tap target definition.
 */
@Composable
fun buildTapTargetDefinition(
    precedence: Int,
    title: String,
    description: String,
): TapTargetDefinition {
    return TapTargetDefinition(
        precedence = precedence,
        title = TextDefinition(
            text = title,
            textStyle = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        ),
        description = TextDefinition(
            text = description,
            textStyle = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary
        ),
        tapTargetStyle = TapTargetStyle(
            backgroundColor = MaterialTheme.colorScheme.surfaceVariant,
            tapTargetHighlightColor = MaterialTheme.colorScheme.inverseOnSurface,
            backgroundAlpha = 1f,
        ),
    )
}