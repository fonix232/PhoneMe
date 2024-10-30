package net.fonix232.phoneme.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import net.fonix232.phoneme.repositories.TodoRepository
import net.fonix232.phoneme.shared.model.TodoItem

/***
 * ViewModel for the ToDo detail screen
 * Takes the ToDo ID as parameter
 * If no ID is provided, a new ToDo will be created
 */
class TodoDetailViewModel(
    private val todoId: String? = null,
    private val repository: TodoRepository
) : ViewModel() {

    private val _todo: MutableStateFlow<TodoItem?> = MutableStateFlow(null)
    val todo: StateFlow<TodoItem?>
        get() = _todo

    init {
        viewModelScope.launch {
            if (todoId != null && todoId != "new") {
                repository.getTodoItemByIdFlow(todoId).collect {
                    _todo.value = it
                }
            } else {
                _todo.value = TodoItem(title = "", description = "")
            }
        }
    }

    fun updateTodo(title: String, description: String) {
        viewModelScope.launch {
            if (todoId != null && todoId != "new") {
                repository.updateTodoItem(todoId, title, description)
            } else {
                repository.insertTodoItem(TodoItem(title = title, description = description))
            }
        }
    }

}
