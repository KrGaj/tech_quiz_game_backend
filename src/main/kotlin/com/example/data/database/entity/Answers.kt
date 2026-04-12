package com.example.data.database.entity

import org.jetbrains.exposed.v1.core.CustomFunction
import org.jetbrains.exposed.v1.core.UuidColumnType
import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.core.dao.id.LongIdTable
import org.jetbrains.exposed.v1.dao.LongEntity
import org.jetbrains.exposed.v1.dao.LongEntityClass
import kotlin.uuid.ExperimentalUuidApi

class Answer(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<Answer>(Answers)

    @OptIn(ExperimentalUuidApi::class)
    var uuid by Answers.uuid
    var user by User referencedOn Answers.user
    var question by Answers.question
    var category by Answers.category
    var isCorrect by Answers.isCorrect
}

object Answers : LongIdTable() {
    private const val CATEGORY_NAME_LENGTH = 20

    @OptIn(ExperimentalUuidApi::class)
    val uuid = uuid(
        name = "uuid",
    ).uniqueIndex()
        .defaultExpression(
            defaultValue = CustomFunction(
                functionName = "uuid_generate_v4",
                columnType = UuidColumnType(),
            )
        )
    val user = reference(
        name = "user_id",
        foreign = Users,
    )
    val question = text(
        name = "question_text",
    )
    val category = varchar(
        name = "category_name",
        length = CATEGORY_NAME_LENGTH,
    )
    val isCorrect = bool(
        name = "is_correct",
    )
}
