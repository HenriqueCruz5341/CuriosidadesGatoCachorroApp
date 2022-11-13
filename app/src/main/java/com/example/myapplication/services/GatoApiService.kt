package com.example.myapplication.services

import com.example.myapplication.dtos.CuriosidadeGatoDto
import retrofit2.Call
import retrofit2.http.GET

interface GatoApiService {

    @GET("fact")
    fun getCuriosidade(): Call<CuriosidadeGatoDto>

}