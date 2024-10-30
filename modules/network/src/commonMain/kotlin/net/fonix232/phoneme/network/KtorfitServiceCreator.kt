import de.jensklingenberg.ktorfit.Ktorfit
import de.jensklingenberg.ktorfit.converter.Converter
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import net.fonix232.phoneme.network.PhoneService
import net.fonix232.phoneme.network.createPhoneService
import net.fonix232.phoneme.network.helpers.ResultConverterFactory

class KtorfitServiceCreator(private val baseUrl: String) {

    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                }
            )
        }
    }

    private val ktorfit = Ktorfit.Builder()
        .converterFactories(ResultConverterFactory())
        .baseUrl(baseUrl)
        .httpClient(httpClient)
        .build()

    fun createPhoneService(): PhoneService = ktorfit.createPhoneService()
}
