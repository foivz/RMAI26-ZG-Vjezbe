package hr.foi.rmai.rmai_zg_vjezbe.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import hr.foi.rmai.rmai_zg_vjezbe.entities.Task
import hr.foi.rmai.rmai_zg_vjezbe.entities.TaskCourse

@Database(version = 1, entities = [Task::class, TaskCourse::class])
abstract class TasksDatabase : RoomDatabase() {
    abstract fun getTasksDao(): TasksDAO
    abstract fun getTaskCoursesDao(): TaskCoursesDAO

    companion object {
        var instanceDb: TasksDatabase? = null

        fun getInstance(): TasksDatabase {
            return instanceDb!!
        }

        fun buildInstance(context: Context) {
            if (instanceDb == null) {
                val instanceBuilder = Room.databaseBuilder(
                    context,
                    TasksDatabase::class.java,
                    "tasks.db"
                )

                instanceBuilder.allowMainThreadQueries()

                instanceDb = instanceBuilder.build()
            }
        }
    }
}

