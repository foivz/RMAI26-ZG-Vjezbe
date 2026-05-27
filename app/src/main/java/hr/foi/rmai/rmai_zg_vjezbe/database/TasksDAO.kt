package hr.foi.rmai.rmai_zg_vjezbe.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import hr.foi.rmai.rmai_zg_vjezbe.entities.Task

@Dao
interface TasksDAO {
    @Query("SELECT * FROM tasks WHERE id = :id")
    fun getTask(id: Int): Task

    @Query("SELECT * FROM tasks WHERE completed = :completed ORDER BY due_date ASC")
    fun getAllTasks(completed: Boolean): List<Task>

    @Insert(onConflict = REPLACE)
    fun insertTask(vararg task: Task): List<Long>

    @Delete
    fun deleteTask(vararg task: Task)
}