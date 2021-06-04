 package com.example.TakeControl.UI

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.TakeControl.R
import com.example.TakeControl.UI.model.Usuario
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

 class CadastroActivity : AppCompatActivity() {
    lateinit var nome : EditText
    lateinit var cpf : EditText
    lateinit var email :EditText
    lateinit var senha : EditText
    lateinit var confirmaSenha : EditText
    lateinit var btn : Button
    lateinit var telacad : View
    lateinit var autenticacaoFirebase: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)
        carregarElementos()
        carregarEventos()
        autenticacaoFirebase = Firebase.auth
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
         cadastraUsuario()
     }

     private fun cadastraUsuario(){
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
                 autenticacaoFirebase.createUserWithEmailAndPassword(
                     email.text.toString(),
                     senha.text.toString()
                 )
                     .addOnCompleteListener(this) { task ->
                         if (task.isSuccessful) {
                             // Sign in success, update UI with the signed-in user's information
                             val user = autenticacaoFirebase.currentUser
                             Intent(this, PrincipalActivity::class.java).apply {
                                 startActivity(this)}
                             lateinit var usuario : Usuario
                             usuario.nome = nome.toString()
                             usuario.cpf = cpf.toString()
                             usuario.email = email.toString()
                             usuario.senha = senha.toString()

                         } else {
                             // If sign in fails, display a message to the user.
                             var excecao = ""
                             try {
                                 throw task.exception!!
                             }catch (e : FirebaseAuthWeakPasswordException){
                                 excecao = "Digite uma senha mais forte!"
                                 msgToast("Digite uma senha mais forte!")
                             }catch (e : FirebaseAuthInvalidCredentialsException){
                                 excecao = "Por favor, digite um e-mail válido";
                                 msgToast("Por favor, digite um e-mail válido")
                             }catch (e : FirebaseAuthUserCollisionException){
                                 excecao = "Esta conta já foi cadastrada";
                                 msgToast("Esta conta já foi cadastrada")
                             }catch (e :Exception){
                                 excecao = "Erro ao cadastrar usuário"
                                 msgToast("Erro ao cadastrar usuário: ")
                                 e.printStackTrace(); //printar no Log o erro
                             }

                         }
                     }
             }
         }
     }

     private fun msgToast(msgToast :String){
         val contexto : Context = applicationContext
         Toast.makeText(contexto, msgToast,Toast.LENGTH_SHORT).show()
     }
 }

