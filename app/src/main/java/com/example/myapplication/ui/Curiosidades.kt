package com.example.myapplication.ui

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityCuriosidadesBinding
import com.example.myapplication.dtos.CuriosidadeCachorroDto
import com.example.myapplication.dtos.CuriosidadeGatoDto
import com.example.myapplication.providers.ClientCachorroApiProvider
import com.example.myapplication.providers.ClientGatoApiProvider
import com.example.myapplication.providers.MyPreferences
import com.example.myapplication.services.CachorroApiService
import com.example.myapplication.services.GatoApiService
import com.example.myapplication.viewModel.CuriosidadesViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Curiosidades : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityCuriosidadesBinding
    private lateinit var preferences: MyPreferences
    private lateinit var curiosidadesVM: CuriosidadesViewModel

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        binding = ActivityCuriosidadesBinding.inflate(layoutInflater)
        preferences = MyPreferences(this)
        setContentView(binding.root)
        curiosidadesVM = ViewModelProvider(this).get(CuriosidadesViewModel::class.java)
        setObserver()

        val nome = preferences.getString("NOME_USUARIO")
        val animal = getCurrentAnimal()

        changeSelectedAnimal(animal)
        curiosidadesVM.requestGatoCuriosidade(animal)

        binding.textOla.text = "${this.getString(R.string.welcome_text)} $nome"

        binding.buttonGerar.setOnClickListener(this)
        binding.imgGato.setOnClickListener(this)
        binding.imgCachorro.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        var animal = getCurrentAnimal()

        if (view.id == R.id.img_gato){
            preferences.setString("ANIMAL_SELECIONADO", "GATO")
            animal = "GATO"
        }else if (view.id == R.id.img_cachorro){
            preferences.setString("ANIMAL_SELECIONADO", "CACHORRO")
            animal = "CACHORRO"
        }

        changeSelectedAnimal(animal)
        curiosidadesVM.requestGatoCuriosidade(animal)
    }

    private fun getCurrentAnimal() : String {
        var animal = preferences.getString("ANIMAL_SELECIONADO")
        if (animal.isEmpty()) {
            animal = "CACHORRO"
            preferences.setString("ANIMAL_SELECIONADO", "CACHORRO")
        }

        return animal
    }

    private fun changeSelectedAnimal(animal: String){
        if (animal == "CACHORRO") {
            binding.imgCachorro.imageTintList = ColorStateList.valueOf(resources.getColor(R.color.yellow, baseContext.theme))
            binding.imgGato.imageTintList = ColorStateList.valueOf(resources.getColor(R.color.white, baseContext.theme))
        }else{
            binding.imgCachorro.imageTintList = ColorStateList.valueOf(resources.getColor(R.color.white, baseContext.theme))
            binding.imgGato.imageTintList = ColorStateList.valueOf(resources.getColor(R.color.yellow, baseContext.theme))
        }
    }
    
    private fun setObserver() {
        curiosidadesVM.getCuriosidade().observe(this, Observer {
            if(it != "")
                binding.textCuriosidade.text = it
            else
                binding.textCuriosidade.text = "Texto da curiosidade"
        })
    }
}