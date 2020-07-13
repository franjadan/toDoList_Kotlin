package com.example.todolist_recyclerview

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val myDataset: MutableList<Task>, private val context: Context) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.task_title)
        val description: TextView = itemView.findViewById(R.id.task_description)

        fun bind(task: Task, context: Context) {
            title.text = task.title
            description.text = task.description

            itemView.setOnClickListener {
                val intent: Intent = Intent(context, DetailActivity::class.java);
                intent.putExtra("Title", task.title)
                intent.putExtra("Description", task.description)
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.MyViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.task, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = myDataset.get(position)
        holder.bind(item, context)
    }

    override fun getItemCount(): Int {
        return myDataset.size
    }

}