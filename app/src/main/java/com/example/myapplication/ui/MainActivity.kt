package com.example.myapplication.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.providers.MyPreferences
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.viewModel.MainViewModel

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var preferences: MyPreferences
    private lateinit var mainMV: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        preferences = MyPreferences(this)
        setContentView(binding.root)

        mainMV = ViewModelProvider(this).get(MainViewModel::class.java)

        binding.buttonEntrar.setOnClickListener(this)
        setOberver()
    }

    override fun onClick(view: View) {
        val nome = binding.inputNome.text.toString()

        if (nome.isNotBlank()) {
            preferences.setString("NOME_USUARIO", nome)
            mainMV.setNome(nome)
        } else {
            Toast.makeText(this, "Digite um nome", Toast.LENGTH_SHORT).show()
        }

        mainMV.setNome(nome)
    }

    private fun setOberver() {
        mainMV.getNome().observe(this) {
            startActivity(Intent(this, Curiosidades::class.java))
            finish()
        }
    }
}
