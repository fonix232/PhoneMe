package net.fonix232.phoneme.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import net.fonix232.phoneme.database.dbo.TodoDbo

@Dao
interface TodoDao {

    @Query("SELECT * FROM todo")
    fun getTodoItemsFlow(): Flow<List<TodoDbo>>

    @Query("SELECT * FROM todo")
    suspend fun getTodoItems(): List<TodoDbo>

    @Query("SELECT * FROM todo WHERE id = :id")
    suspend fun getTodoItemById(id: String): TodoDbo

    @Query("SELECT * FROM todo WHERE id = :id")
    fun getTodoItemByIdFlow(id: String): Flow<TodoDbo>

    @Insert
    suspend fun insertTodoItem(todoItem: TodoDbo)

    @Update
    suspend fun updateTodoItem(todoItem: TodoDbo)

    @Query("DELETE FROM todo WHERE id = :id")
    suspend fun deleteTodoItemById(id: String)
}