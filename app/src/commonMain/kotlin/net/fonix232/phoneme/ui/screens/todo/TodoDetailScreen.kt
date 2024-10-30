package net.fonix232.phoneme.ui.screens.todo

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import net.fonix232.phoneme.ui.controls.FocusedOutlinedTextField
import net.fonix232.phoneme.viewmodel.TodoDetailViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun TodoDetailScreen(
    navController: NavController,
    todoId: String? = null,
    viewModel: TodoDetailViewModel = koinViewModel(
        key = todoId ?: "new",
        parameters = { parametersOf(todoId ?: "new") }
    )
) {
    val todo by viewModel.todo.collectAsState()

    Box(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
        ) {

            val titleState = rememberTextFieldState(todo?.title ?: "")
            val descriptionState = rememberTextFieldState(todo?.description ?: "")

            Text(
                text = if (todoId == null) "New Todo" else todo?.title ?: "",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )

            FocusedOutlinedTextField(
                state = titleState,
                label = { Text("Title") },
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .fillMaxWidth()
            )

            OutlinedTextField(
                state = descriptionState,
                label = { Text("Description") },
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .fillMaxWidth()
            )

            Button(
                onClick = {
                    viewModel.updateTodo(
                        titleState.text.toString(),
                        descriptionState.text.toString()
                    )
                    navController.popBackStack()
                },
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .align(alignment = Alignment.End)
            ) {
                Text("Save")
            }

            Text(
                text = todo?.lastUpdated?.toString() ?: "",
                style = MaterialTheme.typography.bodySmall,
                fontSize = 11.sp,
                modifier = Modifier.padding(vertical = 16.dp)
            )
        }
    }
}


