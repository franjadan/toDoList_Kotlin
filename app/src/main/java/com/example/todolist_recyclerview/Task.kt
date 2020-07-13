package com.example.todolist_recyclerview

import java.util.UUID

class Task {
    var id: String
    var title: String
    var description: String

    constructor(title: String, description: String){
        this.id = UUID.randomUUID().toString()
        this.title = title
        this.description = description
    }
}