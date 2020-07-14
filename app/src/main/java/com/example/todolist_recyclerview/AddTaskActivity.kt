package com.example.todolist_recyclerview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class AddTaskActivity : AppCompatActivity() {

    private lateinit var etTitle: EditText
    private lateinit var etDescription: EditText
    private lateinit var btnSave: Button
    private val REQUEST: Int = 200


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        etTitle = findViewById(R.id.etTitle)
        etDescription = findViewById(R.id.etDescription)
        btnSave = findViewById(R.id.saveButton)

        btnSave.setOnClickListener {
            if (validateTitle() && validateDescription()) {
                val intent: Intent = Intent(this, MainActivity::class.java)
                intent.putExtra("Title", etTitle.text.toString())
                intent.putExtra("Description", etDescription.text.toString())
                this.setResult(REQUEST, intent)
                finish()
            }else{
                Toast.makeText(this,"Debes introducir los datos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateTitle(): Boolean {
        return !(etTitle.text.isEmpty() || etTitle.text.isBlank())
    }

    private fun validateDescription(): Boolean {
        return !(etDescription.text.isEmpty() || etDescription.text.isBlank())
    }
}