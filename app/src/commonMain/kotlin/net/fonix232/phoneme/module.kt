package net.fonix232.phoneme

import net.fonix232.phoneme.database.dbModule
import net.fonix232.phoneme.network.networkModule
import net.fonix232.phoneme.repositories.PhoneRepository
import net.fonix232.phoneme.repositories.TodoRepository
import net.fonix232.phoneme.shared.sharedModule
import net.fonix232.phoneme.viewmodel.PhoneDetailViewModel
import net.fonix232.phoneme.viewmodel.PhoneListViewModel
import net.fonix232.phoneme.viewmodel.TodoDetailViewModel
import net.fonix232.phoneme.viewmodel.TodoListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val repositoryModule = module {
    single { PhoneRepository(get(), get()) }
    single { TodoRepository(get()) }
}

val viewmodelModule = module {
    viewModel { PhoneListViewModel(get()) }
    viewModel { parameters -> PhoneDetailViewModel(phoneId = parameters.get(), get()) }
    viewModel { TodoListViewModel(get()) }
    viewModel { parameters -> TodoDetailViewModel(todoId = parameters.get(), get()) }
}

val appModule = module {
    // TODO
}

val appModules =
    listOf(sharedModule, networkModule, dbModule, repositoryModule, viewmodelModule, appModule)