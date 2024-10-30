package net.fonix232.phoneme.ui.screens.todo

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import net.fonix232.phoneme.ui.controls.TodoListItem
import net.fonix232.phoneme.ui.navigation.TodoDetail
import net.fonix232.phoneme.viewmodel.TodoListViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun TodoListScreen(navController: NavController, viewModel: TodoListViewModel = koinViewModel()) {

    val todoItems by viewModel.todos.collectAsState(emptyList())

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate(TodoDetail()) }) {
                Text("+")
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(todoItems) {
                    TodoListItem(
                        todoItem = it,
                        onClickListener = { navController.navigate(TodoDetail(it.id)) },
                        updateState = { id, isDone -> viewModel.updateTodoState(id, isDone) }
                    )
                }
            }

        }
    }

}