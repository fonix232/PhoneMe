package net.fonix232.phoneme.network.helpers

import de.jensklingenberg.ktorfit.Ktorfit
import de.jensklingenberg.ktorfit.converter.Converter
import de.jensklingenberg.ktorfit.converter.KtorfitResult
import de.jensklingenberg.ktorfit.converter.TypeData
import io.ktor.client.statement.HttpResponse

class ResultConverterFactory: Converter.Factory {
    override fun suspendResponseConverter(
        typeData: TypeData,
        ktorfit: Ktorfit
    ): Converter.SuspendResponseConverter<HttpResponse, *>? {
        if(typeData.typeInfo.type == Result::class) {
            return object:Converter.SuspendResponseConverter<HttpResponse, Result<Any?>> {
                override suspend fun convert(result: KtorfitResult): Result<Any?> {
                    val requestType = typeData.typeArgs.first()

                    return when(result) {
                        is KtorfitResult.Failure -> Result.failure(result.throwable)
                        is KtorfitResult.Success -> Result.success(
                            if(requestType.typeInfo.type == HttpResponse::class) {
                                result.response
                            } else {
                                convertBody(result)
                            }
                        )
                    }
                }

                suspend fun convertBody(result: KtorfitResult): Any? {
                    return ktorfit.nextSuspendResponseConverter(
                        this@ResultConverterFactory,
                        typeData.typeArgs.first()
                    )?.convert(result)
                }

            }
        }
        return null
    }
}