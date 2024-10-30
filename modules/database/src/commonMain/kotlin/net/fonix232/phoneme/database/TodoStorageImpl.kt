package net.fonix232.phoneme.database

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.Clock
import net.fonix232.phoneme.shared.database.TodoStorage
import net.fonix232.phoneme.shared.model.TodoItem

class TodoStorageImpl(private val db: PhoneMeAppDb): TodoStorage {
    override fun getTodoItemsFlow(): Flow<List<TodoItem>> {
        return db.todoDao().getTodoItemsFlow().map { todoList -> todoList.map { todoDbo -> todoDbo.toTodoItem() } }
    }

    override suspend fun getTodoItems(): List<TodoItem> {
        return db.todoDao().getTodoItems().map { todoDbo -> todoDbo.toTodoItem() }
    }

    override suspend fun getTodoItemById(id: String): TodoItem {
        return db.todoDao().getTodoItemById(id).toTodoItem()
    }

    override fun getTodoItemByIdFlow(id: String): Flow<TodoItem> {
        return db.todoDao().getTodoItemByIdFlow(id).map { it.toTodoItem() }
    }

    override suspend fun insertTodoItem(todoItem: TodoItem) {
        db.todoDao().insertTodoItem(todoItem.toDbo())
    }

    override suspend fun insertTodoItems(todoItems: List<TodoItem>) {
        todoItems.forEach { db.todoDao().insertTodoItem(it.toDbo()) }
    }

    override suspend fun deleteTodoItemById(id: String) {
        db.todoDao().deleteTodoItemById(id)
    }

    override suspend fun updateTodoItem(todoItem: TodoItem) {
        db.todoDao().updateTodoItem(todoItem.copy(lastUpdated = Clock.System.now()).toDbo())
    }
}