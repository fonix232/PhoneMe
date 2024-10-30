package net.fonix232.phoneme.database

import net.fonix232.phoneme.shared.database.PhoneStorage
import net.fonix232.phoneme.shared.database.TodoStorage
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dbModule = module {
    singleOf(::AppDbBuilderHolder)
    single { PhoneMeAppDb.buildDatabase(get<AppDbBuilderHolder>().builder) }
    single<PhoneStorage> { PhoneStorageImpl(get()) }
    single<TodoStorage> { TodoStorageImpl(get()) }
}
