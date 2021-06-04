package com.example.TakeControl.UI

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.TakeControl.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity() : AppCompatActivity() {
    lateinit var campoEmail: EditText
    lateinit var campoSenha: EditText
    lateinit var botaoEnviar: Button
    lateinit var botaoCadastrar: Button
    lateinit var autenticacaoFirebase: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        carregarElementos()
        carregarEventos()
        autenticacaoFirebase = Firebase.auth
        //Firebase.auth.signOut()
    }

    public override fun onStart() {
        super.onStart()
        verificaUsuarioLogado()
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

    private fun verificaUsuarioLogado(){
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = autenticacaoFirebase.currentUser
        if(currentUser != null){
            Intent(this, PrincipalActivity::class.java).apply {
                startActivity(this)}
        }
    }

    private fun login(){
        botaoEnviar.setOnClickListener {
            val email = campoEmail.text.toString()
            val senha = campoSenha.text.toString()
            if (email.isEmpty()) {
                campoEmail.error = "Informe o e-mail"
            } else if (senha.isEmpty()) {
                campoSenha.error = "Informe a senha"
            } else{
                loginFirebase(email, senha)
            }
//            else if (email != "jessica@gmail.com" || senha != "123") {
//                Toast.makeText(this,"E-mail e senha não conferem!", Toast.LENGTH_SHORT).show();}
//
//                else {
//                Intent(this, PrincipalActivity::class.java).apply {
//                    startActivity(this)
//                }
//            }
        }
    }

    private fun cadastro(){
        botaoCadastrar.setOnClickListener {
            Intent(this, CadastroActivity::class.java).apply {
                startActivity(this)
            }
        }
    }

    private fun loginFirebase(email: String, senha :String) {
        autenticacaoFirebase.signInWithEmailAndPassword(email, senha)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("TAG", "signInWithEmail:success")
                    val user = autenticacaoFirebase.currentUser
                    Intent(this, PrincipalActivity::class.java).apply {
                        startActivity(this)}

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("TAG", "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Erro ao autenticar",
                        Toast.LENGTH_SHORT).show() }
            }
    }
}

