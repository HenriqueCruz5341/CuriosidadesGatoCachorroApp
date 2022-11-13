package com.example.myapplication.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private var nome = MutableLiveData<String>()

    fun getNome () : LiveData<String> {
        return nome
    }

    fun setNome(nome: String) {
        if (nome.isNotBlank()) {
            this.nome.value = nome
        }
    }

}