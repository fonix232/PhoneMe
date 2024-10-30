package net.fonix232.phoneme.ui.controls

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxState
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import net.fonix232.phoneme.shared.model.TodoItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TodoListItem(
    todoItem: TodoItem,
    onClickListener: () -> Unit = {},
    onDeleteListener: (String) -> Unit = {},
    updateState: (String, Boolean) -> Unit = { _, _ -> }
) {

    val dismissState = rememberSwipeToDismissBoxState(
        initialValue = SwipeToDismissBoxValue.Settled,
        confirmValueChange = {
            when (it) {
                SwipeToDismissBoxValue.StartToEnd, SwipeToDismissBoxValue.EndToStart ->
                    onDeleteListener(todoItem.id)

                SwipeToDismissBoxValue.Settled -> return@rememberSwipeToDismissBoxState false
            }
            return@rememberSwipeToDismissBoxState true
        }
    )

    SwipeToDismissBox(
        state = dismissState,
        backgroundContent = { DeleteBackground(dismissState) },
        enableDismissFromStartToEnd = false,
    ) {
        Card(
            modifier = Modifier
                .combinedClickable(onClick = onClickListener)
        ) {
            ListItem(
                headlineContent = { Text(todoItem.title, maxLines = 1) },
                supportingContent = { Text(todoItem.description, maxLines = 3) },
                leadingContent = {
                    Checkbox(
                        checked = todoItem.isDone,
                        onCheckedChange = { updateState(todoItem.id, it) }
                    )
                }
            )
        }
    }


}


@Composable
fun DeleteBackground(dismissState: SwipeToDismissBoxState) {
    val color = when (dismissState.dismissDirection) {
        SwipeToDismissBoxValue.StartToEnd, SwipeToDismissBoxValue.EndToStart -> Color(0xFFFF1744)
        SwipeToDismissBoxValue.Settled -> Color.Transparent
    }

    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(color)
            .padding(12.dp, 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            Icons.Default.Delete,
            contentDescription = "delete"
        )
        Spacer(modifier = Modifier)
        Icon(
            Icons.Default.Delete,
            contentDescription = "delete"
        )
    }
}