package com.example.todolist_recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var tasks: Tasks

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tasks = Tasks(ArrayList())
        tasks.initTasks()

        viewManager = LinearLayoutManager(this)
        viewAdapter = MyAdapter(tasks.tasks, this);

        recyclerView = findViewById<RecyclerView>(R.id.rv).apply {
            setHasFixedSize(true);
            layoutManager = viewManager;
            adapter = viewAdapter;
        }
    }

}