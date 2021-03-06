package com.example.todolist_recyclerview

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist_recyclerview.Adapters.MyAdapter
import com.example.todolist_recyclerview.Helpers.SwipeToDelete
import com.example.todolist_recyclerview.Models.Task
import com.example.todolist_recyclerview.Models.Tasks
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var tasks: Tasks
    private lateinit var fab: FloatingActionButton
    private val REQUEST: Int = 200
    private lateinit var sharedPref: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPref = getSharedPreferences("Tasks", 0)

        val sharedTasks = sharedPref.getString("Tasks", "")

        if(sharedTasks != ""){
            tasks = Tasks.fromJson(sharedTasks!!)
        }else{
            tasks = Tasks(ArrayList())
        }

        //tasks.initTasks()

        viewManager = LinearLayoutManager(this)
        viewAdapter = MyAdapter(
            tasks.tasks,
            this
        );

        recyclerView = findViewById<RecyclerView>(R.id.rv).apply {
            setHasFixedSize(true);
            layoutManager = viewManager;
            adapter = viewAdapter;
        }

        fab = findViewById(R.id.fab)

        fab.setOnClickListener {
            val intent: Intent = Intent(this, AddTaskActivity::class.java)
            this.startActivityForResult(intent, REQUEST)
        }

        val swipeHandler = object: SwipeToDelete(this) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                //Elimino la tarea

                tasks.removeTask(viewHolder.adapterPosition)

                val editor = sharedPref.edit()

                editor.putString("Tasks", Tasks.toJson(tasks))
                editor.apply()

                recyclerView.adapter!!.notifyDataSetChanged()
            }
        }

        val itemTouchHelper: ItemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 200){
            if(data != null) {
                val title = data.getStringExtra("Title")
                val description = data.getStringExtra("Description")

                //Añado la tarea

                tasks.addTask(
                    Task(
                        title!!,
                        description!!
                    )
                )

                val editor = sharedPref.edit()

                editor.putString("Tasks", Tasks.toJson(tasks))
                editor.apply()

                recyclerView.adapter!!.notifyDataSetChanged()
            }
        }
    }

}