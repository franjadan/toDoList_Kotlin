package com.example.todolist_recyclerview

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    private lateinit var tvTitle: TextView
    private lateinit var tvDescription: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        tvTitle = findViewById(R.id.detail_task_title)
        tvDescription = findViewById(R.id.detail_task_description)

        val title: String? = intent.getStringExtra("Title")
        val description: String? = intent.getStringExtra("Description")

        tvTitle.text = title
        tvDescription.text = description
    }
}