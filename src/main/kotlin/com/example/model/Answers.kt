package com.example.model

import org.jetbrains.exposed.sql.Table

object Answers : Table() {
    val id = long("id").autoIncrement()
    val uuid = uuid("uuid")
    val user = long("user_id") references Users.id
    val question = long("question_id")
    val category = varchar("category_name", 20)
    val isCorrect = bool("is_correct")

    override val primaryKey = PrimaryKey(id)
}