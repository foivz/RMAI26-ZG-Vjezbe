package hr.foi.rmai.rmai_zg_vjezbe.helpers

import hr.foi.rmai.rmai_zg_vjezbe.entities.Task
import hr.foi.rmai.rmai_zg_vjezbe.entities.TaskCourse
import java.util.Date


object MockDataLoader {
    fun getDemoCourses() : List<TaskCourse> {
        return listOf(
            TaskCourse("RMAI", "#000080"),
            TaskCourse("RWA", "#FF0000")
        )
    }
    fun getDemoData() : MutableList<Task> {
        val courses = getDemoCourses()

        return mutableListOf(
            Task(
                "Submit seminar paper",
                Date(),
                courses[0],
                false
            ),
            Task(
                "Prepare for exercises",
                Date(),
                courses[1],
                false
            ),
            Task(
                "Complete project",
                Date(),
                courses[0],
                false
            ),
        )
    }
}