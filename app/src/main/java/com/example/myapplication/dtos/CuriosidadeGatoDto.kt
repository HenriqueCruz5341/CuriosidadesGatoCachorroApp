package com.example.myapplication.dtos

import com.google.gson.annotations.SerializedName

class CuriosidadeGatoDto {

    @SerializedName("fact")
    var fact: String = ""

    @SerializedName("length")
    var length: Int = 0

}