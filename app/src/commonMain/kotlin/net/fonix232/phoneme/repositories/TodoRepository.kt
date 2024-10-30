package net.fonix232.phoneme.repositories

import net.fonix232.phoneme.shared.database.TodoStorage
import net.fonix232.phoneme.shared.model.TodoItem

class TodoRepository(
    private val storage: TodoStorage
) {

    val todoItems = storage.getTodoItemsFlow()

    fun getTodoItemByIdFlow(id: String) = storage.getTodoItemByIdFlow(id)

    suspend fun getTodoItemById(id: String) = storage.getTodoItemById(id)

    suspend fun insertTodoItem(todoItem: TodoItem) = storage.insertTodoItem(todoItem)

    suspend fun updateTodoItem(
        todoId: String,
        title: String? = null,
        description: String? = null,
        isDone: Boolean? = null
    ) {
        val todoItem = getTodoItemById(todoId)
        val updatedItem = todoItem.copy(
            title = title ?: todoItem.title,
            description = description ?: todoItem.description,
            isDone = isDone ?: todoItem.isDone
        )
        storage.updateTodoItem(updatedItem)
    }

    suspend fun deleteTodoItemById(id: String) = storage.deleteTodoItemById(id)

}