package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityMainBinding
    private lateinit var preferences: MyPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        preferences = MyPreferences(this)
        setContentView(binding.root)

        binding.buttonEntrar.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        val nome = binding.inputNome.text.toString()

        if (nome.isNotEmpty()) {
            preferences.setString("NOME_USUARIO", nome)

            startActivity(Intent(this, Curiosidades::class.java))
            finish()
        } else {
            Toast.makeText(applicationContext, "Preencha o nome para entrar", Toast.LENGTH_SHORT).show()
        }
    }
}
