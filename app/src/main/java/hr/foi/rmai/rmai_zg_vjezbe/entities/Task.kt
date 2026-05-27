package hr.foi.rmai.rmai_zg_vjezbe.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Ignore
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import hr.foi.rmai.rmai_zg_vjezbe.converters.DateConverter
import hr.foi.rmai.rmai_zg_vjezbe.database.TasksDatabase
import java.util.Date

@Entity(
    tableName = "tasks",
    foreignKeys = [
        ForeignKey(
            entity = TaskCourse::class,
            parentColumns = ["id"],
            childColumns = ["course_id"],
            onDelete = ForeignKey.RESTRICT
        )
    ]
)
@TypeConverters(DateConverter::class)
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    @ColumnInfo(name="due_date") val dueDate: Date,
    @ColumnInfo(name="course_id") val courseId: Int,
    var completed: Boolean
) {
    @delegate:Ignore
    val course: TaskCourse by lazy {
        TasksDatabase.getInstance().getTaskCoursesDao().getCourseById(courseId)
    }
}
