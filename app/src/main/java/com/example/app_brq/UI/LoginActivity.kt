package com.example.app_brq.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.app_brq.R

class LoginActivity() : AppCompatActivity() {
    lateinit var campoEmail: EditText
    lateinit var campoSenha: EditText
    lateinit var botaoEnviar: Button
    lateinit var botaoCadastrar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        carregarElementos()
        carregarEventos()
    }

    private fun carregarElementos() {
        campoEmail = findViewById(R.id.inputEmailP)
        campoSenha = findViewById(R.id.inputSenha)
        botaoEnviar = findViewById(R.id.btnLogin)
        botaoCadastrar = findViewById(R.id.btnCadastro)
    }

    private fun carregarEventos() {
        login()
        cadastro()
    }

    private fun login(){
        botaoEnviar.setOnClickListener {
            val email = campoEmail.text.toString()
            val senha = campoSenha.text.toString()
            if (email.isEmpty() == true) {
                campoEmail.error = "campo em branco"
            } else if (senha.isEmpty() == true) {
                campoSenha.error = "campo em branco"
            } else if ((email != "gabriel@brq.com" && senha != "123") || (email != "jessica@brq.com" && senha != "123") || (email != "flavia@brq.com" && senha != "123")) {
                Intent(this, CadastroActivity::class.java).apply {
                    startActivity(this)
                }
            } else {
                Intent(this, PrincipalActivity::class.java).apply {
                    startActivity(this)
                }
            }
        }
    }

    private fun cadastro(){
        botaoCadastrar.setOnClickListener {
            Intent(this, CadastroActivity::class.java).apply {
                startActivity(this)
            }
        }
    }
}

