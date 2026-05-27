package hr.foi.rmai.rmai_zg_vjezbe.helpers

import hr.foi.rmai.rmai_zg_vjezbe.database.TasksDatabase
import hr.foi.rmai.rmai_zg_vjezbe.entities.Task
import hr.foi.rmai.rmai_zg_vjezbe.entities.TaskCourse
import java.util.Date


object MockDataLoader {
    fun getDemoCourses() : List<TaskCourse> {
        return listOf(
            TaskCourse(0, "RMAI", "#000080"),
            TaskCourse(1, "RWA", "#FF0000")
        )
    }
    fun getDemoData() : MutableList<Task> {
        val courses = getDemoCourses()

        return mutableListOf(
            Task(0,
                "Submit seminar paper",
                Date(),
                courses[0].id,
                false
            ),
            Task(1,
                "Prepare for exercises",
                Date(),
                courses[1].id,
                false
            ),
            Task(2,
                "Complete project",
                Date(),
                courses[0].id,
                false
            ),
        )
    }

    fun loadMockData() {
        val tasksDao = TasksDatabase.getInstance().getTasksDao()
        val taskCoursesDao = TasksDatabase.getInstance().getTaskCoursesDao()
        if (tasksDao.getAllTasks(false).isEmpty() &&
            tasksDao.getAllTasks(true).isEmpty() &&
            taskCoursesDao.getAllCourses().isEmpty()
        ) {
            val courses = arrayOf(
                TaskCourse(1, "RMAI", "#000080"),
                TaskCourse(2, "RWA", "#FF0000"),
                TaskCourse(3, "SIS", "#CCCCCC")
            )
            taskCoursesDao.insertCourse(*courses)

            val dbCourses = taskCoursesDao.getAllCourses()
            val tasks = arrayOf(
                Task(1, "Submit seminar paper", Date(), dbCourses[0].id, false),
                Task(2, "Prepare for exercises", Date(),dbCourses[1].id, false),
                Task(3, "Rally a project team", Date(), dbCourses[0].id, false),
                Task(4, "Work on 1st homework", Date(), dbCourses[2].id, false)
            )
            tasksDao.insertTask(*tasks)
        }
    }
}