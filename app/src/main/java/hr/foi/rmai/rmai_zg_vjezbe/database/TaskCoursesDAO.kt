package hr.foi.rmai.rmai_zg_vjezbe.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import hr.foi.rmai.rmai_zg_vjezbe.entities.TaskCourse

@Dao
interface TaskCoursesDAO {
    @Query("SELECT * FROM task_courses")
    fun getAllCourses(): List<TaskCourse>

    @Query("SELECT * FROM task_courses WHERE id = :id")
    fun getCourseById(id: Int): TaskCourse

    @Insert
    fun insertCourse(vararg course: TaskCourse)
}