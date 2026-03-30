package com.taskflow.todoapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class MainActivity : AppCompatActivity(), TaskAdapter.TaskListener {

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore
    private lateinit var adapter: TaskAdapter
    private lateinit var userId: String
    private lateinit var recyclerView: RecyclerView
    private lateinit var emptyMessage: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        if (auth.currentUser == null) {
            finish()
            return
        }

        userId = auth.currentUser!!.uid

        recyclerView = findViewById(R.id.recyclerTasks)
        emptyMessage = findViewById(R.id.emptyMessage)
        val btnLogout = findViewById<Button>(R.id.btnLogout)
        val fabAdd = findViewById<FloatingActionButton>(R.id.fabAddTask)

        adapter = TaskAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        btnLogout.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        fabAdd.setOnClickListener {
            startActivity(Intent(this, TaskActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        loadTasks()
    }

    private fun loadTasks() {
        db.collection("usuarios")
            .document(userId)
            .collection("tarefas")
            .get()
            .addOnSuccessListener { result ->
                updateTaskList(result)
            }
            .addOnFailureListener {
                emptyMessage.text = "Erro ao carregar tarefas"
                emptyMessage.visibility = View.VISIBLE
            }
    }

    private fun updateTaskList(result: QuerySnapshot) {
        val tasks = result.documents.map { document ->
            document.toObject(Task::class.java)!!.copy(id = document.id)
        }

        adapter.submitList(tasks)
        emptyMessage.visibility = if (tasks.isEmpty()) View.VISIBLE else View.GONE
    }

    override fun onEdit(task: Task) {
        val intent = Intent(this, TaskActivity::class.java)
        intent.putExtra("taskId", task.id)
        intent.putExtra("taskTitle", task.title)
        intent.putExtra("taskDescription", task.description)
        startActivity(intent)
    }

    override fun onDelete(task: Task) {
        db.collection("usuarios")
            .document(userId)
            .collection("tarefas")
            .document(task.id)
            .delete()
            .addOnSuccessListener {
                loadTasks()
            }
    }
}