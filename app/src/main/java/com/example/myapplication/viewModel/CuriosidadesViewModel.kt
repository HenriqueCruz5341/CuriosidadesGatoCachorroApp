package com.example.myapplication.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.dtos.CuriosidadeCachorroDto
import com.example.myapplication.dtos.CuriosidadeGatoDto
import com.example.myapplication.providers.ClientCachorroApiProvider
import com.example.myapplication.providers.ClientGatoApiProvider
import com.example.myapplication.services.CachorroApiService
import com.example.myapplication.services.GatoApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CuriosidadesViewModel : ViewModel() {

    private var curiosidade = MutableLiveData<String>()

    fun getCuriosidade() : LiveData<String> {
        return curiosidade
    }

    fun requestGatoCuriosidade(animal: String) {
        if (animal == "CACHORRO") {
            val apiCachorroService = ClientCachorroApiProvider.createService(CachorroApiService::class.java)
            val curiosidadeCachorro: Call<CuriosidadeCachorroDto> = apiCachorroService.getCuriosidades(1)

            curiosidadeCachorro.enqueue(object : Callback<CuriosidadeCachorroDto> {
                override fun onResponse(call: Call<CuriosidadeCachorroDto>, response: Response<CuriosidadeCachorroDto>){
                    curiosidade.value = response.body()?.facts?.get(0)
                }

                override fun onFailure(call: Call<CuriosidadeCachorroDto>, t: Throwable) {
                    curiosidade.value = "Erro ao buscar curiosidade"
                }
            })
        }else{
            val apiGatoService = ClientGatoApiProvider.createService(GatoApiService::class.java)
            val curiosidadeGato: Call<CuriosidadeGatoDto> = apiGatoService.getCuriosidade()

            curiosidadeGato.enqueue(object : Callback<CuriosidadeGatoDto> {
                override fun onResponse(call: Call<CuriosidadeGatoDto>, response: Response<CuriosidadeGatoDto>){
                    curiosidade.value = response.body()?.fact
                }

                override fun onFailure(call: Call<CuriosidadeGatoDto>, t: Throwable) {
                    curiosidade.value = "Erro ao buscar curiosidade"
                }
            })
        }
    }

}