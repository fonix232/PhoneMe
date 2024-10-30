package net.fonix232.phoneme.network

import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.DELETE
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.POST
import de.jensklingenberg.ktorfit.http.PUT
import de.jensklingenberg.ktorfit.http.Query
import net.fonix232.phoneme.network.dto.PhoneDto

interface PhoneService {

    @GET("/objects")
    suspend fun getPhones(): Result<List<PhoneDto>>

    @GET("/objects")
    suspend fun getPhonesById(@Query("id") ids: List<Number>): Result<List<PhoneDto>>

    @POST("/objects")
    suspend fun addPhone(@Body phone: PhoneDto)

    @PUT("/objects")
    suspend fun updatePhone(@Query("id") id: Number, @Body phone: PhoneDto)

    @DELETE("/objects")
    suspend fun deletePhone(@Query("id") id: Number)
}
