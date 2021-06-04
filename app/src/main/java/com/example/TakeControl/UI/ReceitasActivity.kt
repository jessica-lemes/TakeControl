package com.example.TakeControl.UI

import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.TakeControl.R
import com.example.TakeControl.UI.adapter.AdapterMovimentacoes
import com.example.TakeControl.UI.model.Movimentacao
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*
import kotlin.collections.ArrayList


class ReceitasActivity : AppCompatActivity() {

    lateinit var recyclerReceitas: RecyclerView
    lateinit var editTextValorReceita: TextView
    lateinit var editTextDataReceita: TextView
    lateinit var editTextCategoriaReceita: TextView
    lateinit var editTextDescricaoReceita: TextView
    lateinit var fabReceita: FloatingActionButton

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receitas)

        carregarElementos()
        carregarEventos()
        carregaCalendario()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onRestart() {
        super.onRestart()
        carregarElementos()
        carregarEventos()
        carregaCalendario()
    }

    private fun carregarElementos() {
        recyclerReceitas = findViewById(R.id.recyclerReceitas)
        editTextValorReceita = findViewById(R.id.editTextValorReceita)
        editTextDataReceita = findViewById(R.id.editTextDataReceita)
        editTextCategoriaReceita = findViewById(R.id.editTextCategoriaReceita)
        editTextDescricaoReceita = findViewById(R.id.editTextDescricaoReceita)
        fabReceita = findViewById(R.id.fabReceita)
    }

    private fun carregarEventos() {
        atualizaRecycler()
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun enviaDados(view: View){

        val valor = editTextValorReceita.text
        val data = editTextDataReceita.text
        var categoria = editTextCategoriaReceita.text
        var descricao = editTextDescricaoReceita.text
        lateinit var valorString :String

        if (valor.isNullOrEmpty()) {
            editTextValorReceita.error = "Informe um valor"
        }else if (data.isNullOrEmpty()) {
            editTextDataReceita.error = "Informe uma data"
        }else if (categoria.isNullOrEmpty()) {
            categoria = ""
        }else if (descricao.isNullOrEmpty()) {
            descricao = ""
        }
        else{
            valorString = valor.toString()
        }

       val dados = Movimentacao(valor.toString().toDouble(), data.toString(), categoria.toString(), descricao.toString(), "Receita")
        dados.adicionaMovimentacao()
        limpaCampos()
        atualizaRecycler()
    }

    fun atualizaRecycler(){
        val lista: ArrayList<Movimentacao> = ListaGlobal.retornaListaMovimentacao()
        var listaFiltrada = lista.filter { it.tipoMovimentacao == "Receita" }
        recyclerReceitas.adapter = AdapterMovimentacoes(ArrayList(listaFiltrada), this)
        recyclerReceitas.layoutManager = LinearLayoutManager(this)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun carregaCalendario(){
        val calendario = Calendar.getInstance()
        var ano = calendario.get(Calendar.YEAR)
        var mes = calendario.get(Calendar.MONTH)
        var dia = calendario.get(Calendar.DAY_OF_MONTH)

        editTextDataReceita.setOnClickListener {
            val datePicker = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { DatePicker, ano, mes, dia ->
                editTextDataReceita.text = "" + dia + "/" + (mes + 1) + "/" + ano
            }, ano, mes, dia)

            datePicker.show()
        }
    }

    fun limpaCampos(){
        editTextValorReceita.text = null
        editTextDataReceita.text = null
        editTextCategoriaReceita.text = null
        editTextDescricaoReceita.text = null
    }


}