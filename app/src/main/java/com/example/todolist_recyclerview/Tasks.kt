package com.example.todolist_recyclerview

import com.google.gson.Gson

data class Tasks(val tasks: MutableList<Task>) {

    fun initTasks() {
        tasks.add(Task("Lorem ipsum", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec ligula dolor, suscipit ac interdum ut, convallis vitae mi. Mauris eget felis et ipsum luctus auctor vitae nec nibh. Nulla facilisi. Nam vulputate lectus ac erat ultricies, eget efficitur tortor suscipit."))
        tasks.add(Task("Lorem ipsum", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec ligula dolor, suscipit ac interdum ut, convallis vitae mi. Mauris eget felis et ipsum luctus auctor vitae nec nibh. Nulla facilisi. Nam vulputate lectus ac erat ultricies, eget efficitur tortor suscipit."))
        tasks.add(Task("Lorem ipsum", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec ligula dolor, suscipit ac interdum ut, convallis vitae mi. Mauris eget felis et ipsum luctus auctor vitae nec nibh. Nulla facilisi. Nam vulputate lectus ac erat ultricies, eget efficitur tortor suscipit."))
    }

    fun addTask(task: Task) {
        tasks.add(task)
    }

    fun removeTask(position: Int) {
        tasks.removeAt(position)
    }

    fun toJson(): String {
        val gson: Gson = Gson();
        val json: String = gson.toJson(this);
        return json;
    }

    fun fromJson(json: String): Tasks {
        val gson: Gson = Gson();
        return gson.fromJson(json, Tasks::class.java);
    }
}