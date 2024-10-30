package net.fonix232.phoneme.shared.database

import kotlinx.coroutines.flow.Flow
import net.fonix232.phoneme.shared.model.TodoItem

interface TodoStorage {
    fun getTodoItemsFlow(): Flow<List<TodoItem>>
    fun getTodoItemByIdFlow(id: String): Flow<TodoItem>
    suspend fun getTodoItemById(id: String): TodoItem
    suspend fun getTodoItems(): List<TodoItem>
    suspend fun insertTodoItem(todoItem: TodoItem)
    suspend fun insertTodoItems(todoItems: List<TodoItem>)
    suspend fun deleteTodoItemById(id: String)
    suspend fun updateTodoItem(todoItem: TodoItem)
}
