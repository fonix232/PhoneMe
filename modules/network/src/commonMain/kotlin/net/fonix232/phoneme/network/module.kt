package net.fonix232.phoneme.network

import KtorfitServiceCreator
import net.fonix232.phoneme.shared.network.PhoneApi
import org.koin.dsl.module

val networkModule = module {
    single { KtorfitServiceCreator("https://api.restful-api.dev/") }
    single { get<KtorfitServiceCreator>().createPhoneService() }
    single<PhoneApi> { PhoneApiImpl(get<PhoneService>()) }
}
