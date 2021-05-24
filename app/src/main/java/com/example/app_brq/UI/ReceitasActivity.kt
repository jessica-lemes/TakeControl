package com.example.app_brq.UI

import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app_brq.R
import com.example.app_brq.UI.adapter.AdapterMovimentacoes
import com.example.app_brq.UI.model.Movimentacao
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.DateFormat
import java.text.NumberFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList


class ReceitasActivity : AppCompatActivity() {

    lateinit var recyclerReceitas: RecyclerView
    lateinit var editTextValorReceita: TextView
    lateinit var editTextDataReceita: TextView
    lateinit var editTextCategoriaReceita: TextView
    lateinit var editTextDescricaoReceita: TextView
    lateinit var fabReceita: FloatingActionButton
    lateinit var dataCalendario: Calendar

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
    fun mostraDadoOnClick(view: View){

        val valor = editTextValorReceita.text
        val valorString = valor.toString()
        val data = editTextDataReceita.text
        val categoria = editTextCategoriaReceita.text
        val descricao = editTextDescricaoReceita.text


        val dados = Movimentacao(valorString.toDouble(), data.toString() ,categoria.toString(),descricao.toString() ,"Receita")
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
                editTextDataReceita.text = ""+dia+"/"+(mes+1)+"/"+ano
                //dataCalendario.set(ano,mes,dia)
            }, ano, mes, dia )

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