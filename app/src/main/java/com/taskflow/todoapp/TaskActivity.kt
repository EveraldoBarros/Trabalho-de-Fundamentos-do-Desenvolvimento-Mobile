package com.taskflow.todoapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class TaskActivity : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        val editTitle = findViewById<EditText>(R.id.editTaskTitle)
        val editDescription = findViewById<EditText>(R.id.editTaskDescription)
        val btnSave = findViewById<Button>(R.id.btnSaveTask)

        val taskId = intent.getStringExtra("taskId")
        val taskTitle = intent.getStringExtra("taskTitle")
        val taskDescription = intent.getStringExtra("taskDescription")

        // Preencher campos se for edição
        if (!taskId.isNullOrEmpty()) {
            editTitle.setText(taskTitle)
            editDescription.setText(taskDescription)
        }

        btnSave.setOnClickListener {
            val title = editTitle.text.toString().trim()
            val description = editDescription.text.toString().trim()
            val userId = auth.currentUser?.uid

            if (userId == null) {
                Toast.makeText(this, "Usuário não autenticado", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (title.isEmpty()) {
                Toast.makeText(this, "Digite um título", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val tarefasRef = db.collection("usuarios")
                .document(userId)
                .collection("tarefas")

            if (taskId.isNullOrEmpty()) {
                // NOVA TAREFA
                val docRef = tarefasRef.document()
                val task = Task(docRef.id, title, description)

                docRef.set(task)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Tarefa criada", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Erro ao criar tarefa", Toast.LENGTH_SHORT).show()
                    }

            } else {
                // EDITAR TAREFA
                val task = Task(taskId, title, description)

                tarefasRef.document(taskId)
                    .set(task)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Tarefa atualizada", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Erro ao atualizar tarefa", Toast.LENGTH_SHORT).show()
                    }
            }
        }
    }
}