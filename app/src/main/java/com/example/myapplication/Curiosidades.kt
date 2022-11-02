package com.example.myapplication

import Dados
import android.annotation.SuppressLint
import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.myapplication.databinding.ActivityCuriosidadesBinding
import kotlin.random.Random

class Curiosidades : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityCuriosidadesBinding
    private lateinit var preferences: MyPreferences

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        binding = ActivityCuriosidadesBinding.inflate(layoutInflater)
        preferences = MyPreferences(this)
        setContentView(binding.root)

        val nome = preferences.getString("NOME_USUARIO")
        val animal = getCurrentAnimal()

        changeSelectedAnimal(animal)
        changeCuriosidade(animal)

        binding.textOla.text = "Olá, $nome"

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
        changeCuriosidade(animal)
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

    private fun changeCuriosidade(animal: String) {
        if (animal == "CACHORRO") {
            binding.textCuriosidade.text = Dados.cachorro[Random.nextInt(0, 5)]
        }else{
            binding.textCuriosidade.text = Dados.gato[Random.nextInt(0, 5)]
        }
    }
}