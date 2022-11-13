package com.example.myapplication.dtos

import com.google.gson.annotations.SerializedName

class CuriosidadeCachorroDto {

    @SerializedName("facts")
    var facts: List<String> = listOf()
}