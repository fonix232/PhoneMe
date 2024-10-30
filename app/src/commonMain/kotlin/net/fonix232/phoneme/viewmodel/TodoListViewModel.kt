package net.fonix232.phoneme.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import net.fonix232.phoneme.repositories.TodoRepository

class TodoListViewModel(private val repository: TodoRepository) : ViewModel() {

    val todos = repository.todoItems

    fun updateTodoState(todoId: String, state: Boolean) {
        viewModelScope.launch {
            repository.updateTodoItem(todoId, isDone = state)
        }
    }

    fun removeTodo(todoId: String) {
        viewModelScope.launch {
            repository.deleteTodoItemById(todoId)
        }

    }
}