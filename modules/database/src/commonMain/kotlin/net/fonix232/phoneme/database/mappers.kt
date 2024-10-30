package net.fonix232.phoneme.database

import kotlinx.datetime.Clock
import net.fonix232.phoneme.database.dbo.PhoneDbo
import net.fonix232.phoneme.database.dbo.TodoDbo
import net.fonix232.phoneme.shared.model.Phone
import net.fonix232.phoneme.shared.model.TodoItem

fun PhoneDbo.toPhone(): Phone = Phone(
    id = id,
    name = name,
    price = price,
    color = color,
    capacity = capacity,
    generation = generation,
    lastUpdated = lastUpdated ?: Clock.System.now()
)

fun Phone.toDbo(): PhoneDbo = PhoneDbo(
    id = id.toInt(),
    name = name,
    price = price?.toDouble(),
    color = color,
    capacity = capacity,
    generation = generation,
    lastUpdated = lastUpdated ?: Clock.System.now()
)

fun TodoItem.toDbo(): TodoDbo = TodoDbo(
    id = id,
    title = title,
    description = description,
    isDone = isDone,
    lastUpdated = lastUpdated
)

fun TodoDbo.toTodoItem(): TodoItem = TodoItem(
    id = id,
    title = title,
    description = description,
    isDone = isDone,
    lastUpdated = lastUpdated
)
