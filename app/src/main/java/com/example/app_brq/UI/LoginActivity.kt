package com.example.app_brq.UI

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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
            if (email.isEmpty()) {
                campoEmail.error = "Informe o e-mail"
            } else if (senha.isEmpty()) {
                campoSenha.error = "Informe a senha"
            } else if (email != "jessica@gmail.com" || senha != "123") {
                Toast.makeText(this,"E-mail e senha não conferem!", Toast.LENGTH_SHORT).show();
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

