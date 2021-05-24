package com.example.app_brq.UI

import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app_brq.R
import com.example.app_brq.UI.adapter.AdapterMovimentacoes
import com.example.app_brq.UI.model.Movimentacao
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.NumberFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class DespesasActivity : AppCompatActivity() {

    lateinit var recyclerDespesas: RecyclerView
    lateinit var editTextValorDespesa: TextView
    lateinit var editTextDataDespesa: TextView
    lateinit var editTextCategoriaDespesa: TextView
    lateinit var editTextDescricaoDespesa: TextView
    lateinit var fabDespesa: FloatingActionButton
    lateinit var dataCalendario: Calendar


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_despesas)

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
        recyclerDespesas = findViewById(R.id.recyclerDespesas)
        editTextValorDespesa = findViewById(R.id.editTextValorDespesa)
        editTextDataDespesa = findViewById(R.id.editTextDataDespesa)
        editTextCategoriaDespesa = findViewById(R.id.editTextCategoriaDespesa)
        editTextDescricaoDespesa = findViewById(R.id.editTextDescricaoDespesa)
        fabDespesa = findViewById(R.id.fabDespesa)
    }

    private fun carregarEventos() {
        atualizaRecycler()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun mostraDadoOnClick(view: View) {
        val valor = "-" + editTextValorDespesa.text
        val data = editTextDataDespesa.text
        val categoria = editTextCategoriaDespesa.text
        val descricao = editTextDescricaoDespesa.text

        val dados = Movimentacao( valor.toDouble(), data.toString(), categoria.toString(), descricao.toString(), "Despesa")
        dados.adicionaMovimentacao()
        limpaCampos()
        atualizaRecycler()
    }

    fun atualizaRecycler() {
        val lista: ArrayList<Movimentacao> = ListaGlobal.retornaListaMovimentacao()
        var listaFiltrada = lista.filter { it.tipoMovimentacao=="Despesa" }
        recyclerDespesas.adapter = AdapterMovimentacoes(ArrayList(listaFiltrada), this)
        recyclerDespesas.layoutManager = LinearLayoutManager(this)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun carregaCalendario() {
        val calendario = Calendar.getInstance()
        var ano = calendario.get(Calendar.YEAR)
        var mes = calendario.get(Calendar.MONTH)
        var dia = calendario.get(Calendar.DAY_OF_MONTH)

        editTextDataDespesa.setOnClickListener {
            val datePicker = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { DatePicker, ano, mes, dia ->
                editTextDataDespesa.text = "" + dia + "/" + (mes + 1) + "/" + ano
                //dataCalendario.set(ano,mes,dia)
            }, ano, mes, dia)

            datePicker.show()
        }
    }
    fun limpaCampos(){
        editTextValorDespesa.text = null
        editTextDataDespesa.text = null
        editTextCategoriaDespesa.text = null
        editTextDescricaoDespesa.text = null
    }
}