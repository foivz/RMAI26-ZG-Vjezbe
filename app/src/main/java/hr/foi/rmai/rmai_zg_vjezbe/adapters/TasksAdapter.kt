package hr.foi.rmai.rmai_zg_vjezbe.adapters

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.SurfaceView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.graphics.toColorInt
import androidx.recyclerview.widget.RecyclerView
import hr.foi.rmai.rmai_zg_vjezbe.R
import hr.foi.rmai.rmai_zg_vjezbe.database.TasksDatabase
import hr.foi.rmai.rmai_zg_vjezbe.entities.Task
import org.w3c.dom.Text
import java.text.SimpleDateFormat
import java.util.Locale

class TasksAdapter(private val tasksList: MutableList<Task>,
                   private val onTaskCompleted: ((taskId: Int) -> Unit)? = null
    ) : RecyclerView.Adapter<TasksAdapter.TaskViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TaskViewHolder {
        val taskView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.task_list_item, parent, false)

        return TaskViewHolder(taskView)
    }

    override fun onBindViewHolder(
        holder: TaskViewHolder,
        position: Int
    ) {
        holder.bind(tasksList[position])
    }

    override fun getItemCount(): Int {
        return tasksList.size
    }

    inner class TaskViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val sdf = SimpleDateFormat("dd.MM.yyyy. HH:mm", Locale.ENGLISH)
        private val taskName: TextView
        private val taskDueDate: TextView
        private val taskCourseColor: SurfaceView

        init {
            taskName = view.findViewById(R.id.tv_task_name)
            taskDueDate = view.findViewById(R.id.tv_task_due_date)
            taskCourseColor = view.findViewById(R.id.sv_task_course_color)

            view.setOnLongClickListener {
                AlertDialog.Builder(view.context)
                    .setTitle(taskName.text)
                    .setNeutralButton("Delete task") { _, _ ->
                        val deletedTask = tasksList[adapterPosition]
                        TasksDatabase.getInstance().getTasksDao().deleteTask(deletedTask)
                        removeTaskFromList()
                    }
                    .setPositiveButton("Mark as completed") { _, _ ->
                        val completedTask = tasksList[adapterPosition]
                        completedTask.completed = true
                        TasksDatabase.getInstance().getTasksDao().insertTask(completedTask)
                        removeTaskFromList()

                        if (onTaskCompleted != null) {
                            onTaskCompleted?.invoke(completedTask.id)
                        }
                    }
                    .setNegativeButton("Cancel") { dialog, _ ->
                        dialog.cancel()
                    }
                    .show()

                return@setOnLongClickListener true
            }
        }

        private fun removeTaskFromList() {
            tasksList.removeAt(adapterPosition)
            notifyItemRemoved(adapterPosition)
        }

        fun bind(task: Task) {
            taskName.text = task.name
            taskDueDate.text = sdf.format(task.dueDate)
            taskCourseColor.setBackgroundColor(task.course.color.toColorInt())
        }
    }

    fun addTask(newTask: Task) {
        var newIndexInList = tasksList.indexOfFirst { task ->
            task.dueDate > newTask.dueDate
        }

        if (newIndexInList == -1) {
            newIndexInList = tasksList.size
        }

        tasksList.add(newIndexInList, newTask)
        notifyItemInserted(newIndexInList)
    }
}








