package com.example.data.database.entity

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.CustomFunction
import org.jetbrains.exposed.sql.UUIDColumnType

class Answer(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<Answer>(Answers)
    var uuid by Answers.uuid
    var user by User referencedOn Answers.user
    var question by Answers.question
    var category by Answers.category
    var isCorrect by Answers.isCorrect
}

object Answers : LongIdTable() {
    val uuid = uuid("uuid").uniqueIndex()
        .defaultExpression(CustomFunction("uuid_generate_v4", UUIDColumnType()))
    val user = reference("user_id", Users)
    val question = long("question_id")
    val category = varchar("category_name", 20)
    val isCorrect = bool("is_correct")
}
