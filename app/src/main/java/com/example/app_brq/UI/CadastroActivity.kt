 package com.example.app_brq.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.example.app_brq.R

class CadastroActivity : AppCompatActivity() {
    lateinit var nome : EditText
    lateinit var cpf : EditText
    lateinit var email :EditText
    lateinit var senha : EditText
    lateinit var confirmaSenha : EditText
    lateinit var btn : Button
    lateinit var telacad : View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        carregarElementos()
        carregarEventos()
    }

    private fun carregarElementos() {
        nome = findViewById(R.id.inputNome)
        cpf = findViewById(R.id.inputCPF)
        email = findViewById(R.id.inputEmailP)
        senha = findViewById(R.id.inputSenhaCad)
        confirmaSenha = findViewById(R.id.inputConfirmaSenha)
        btn = findViewById(R.id.btnCadastrar)
        telacad = findViewById(R.id.TelaCad)
    }
    private fun carregarEventos() {
                btn.setOnClickListener{
                    if (nome.text.isEmpty()){
                        nome.error = "Informe um nome"
                    }else if (cpf.text.isEmpty()){
                        cpf.error = "Informe um CPF"
                    }else if (email.text.isEmpty()){
                        email.error = "Informe um e-mail"
                    }else if (senha.text.isEmpty()){
                        senha.error = " Informe uma senha"
                    }else if(confirmaSenha.text.isEmpty()) {
                        confirmaSenha.error = "Confirme a senha"
                    }else if (confirmaSenha.text.toString() != senha.text.toString()){
                        confirmaSenha.error = "Senhas não conferem"
                    }
                    else {
                        Intent(this, PrincipalActivity::class.java).apply {
                            startActivity(this)
                        }
                    }
                }
    }
}
