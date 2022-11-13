package com.example.myapplication.services

import com.example.myapplication.dtos.CuriosidadeCachorroDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CachorroApiService {

    @GET("api/v1/facts")
    fun getCuriosidades(@Query("number")number: Int): Call<CuriosidadeCachorroDto>

}