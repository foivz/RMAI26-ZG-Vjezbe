package hr.foi.rmai.rmai_zg_vjezbe.entities

import java.util.Date

data class Task(
    val name: String,
    val dueDate: Date,
    val course: TaskCourse,
    val completed: Boolean
)
