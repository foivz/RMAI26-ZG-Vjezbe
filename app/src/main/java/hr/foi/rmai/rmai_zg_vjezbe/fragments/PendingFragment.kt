package hr.foi.rmai.rmai_zg_vjezbe.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import hr.foi.rmai.rmai_zg_vjezbe.R
import hr.foi.rmai.rmai_zg_vjezbe.adapters.TasksAdapter
import hr.foi.rmai.rmai_zg_vjezbe.database.TasksDatabase
import hr.foi.rmai.rmai_zg_vjezbe.helpers.MockDataLoader
import hr.foi.rmai.rmai_zg_vjezbe.helpers.NewTaskDialogHelper

class PendingFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var btnCreateTask: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pending, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val tasks = TasksDatabase.getInstance().getTasksDao().getAllTasks(false)

        recyclerView = view.findViewById(R.id.rv_pending_tasks)
        recyclerView.adapter = TasksAdapter(tasks.toMutableList()) { taskId ->
            parentFragmentManager.setFragmentResult(
                "task_completed",
                bundleOf("task_id" to taskId)
            )
        }
        recyclerView.layoutManager = LinearLayoutManager(view.context)


        btnCreateTask = view.findViewById(R.id.fab_create_task)
        btnCreateTask.setOnClickListener {
            showDialog()
        }
    }

    private fun showDialog() {
        val newTaskDialogView = LayoutInflater
            .from(context)
            .inflate(R.layout.new_task_dialog, null)

        val dialogHelper = NewTaskDialogHelper(newTaskDialogView)

        AlertDialog.Builder(requireContext())
            .setView(newTaskDialogView)
            .setTitle(getString(R.string.create_new_task))
            .setPositiveButton(getString(R.string.create_new_task)) { _, _ ->
                var newTask = dialogHelper.buildTask()

                val tasksDao = TasksDatabase.getInstance().getTasksDao()
                val newTaskId = tasksDao.insertTask(newTask)

                newTask = tasksDao.getTask(newTaskId[0].toInt())

                val tasksAdapter = recyclerView.adapter as TasksAdapter
                tasksAdapter.addTask(newTask)
            }
            .show()

        val courses = TasksDatabase.getInstance().getTaskCoursesDao().getAllCourses()
        dialogHelper.populateSpinner(courses)
        dialogHelper.activateDateTimeListeners()
    }
}






