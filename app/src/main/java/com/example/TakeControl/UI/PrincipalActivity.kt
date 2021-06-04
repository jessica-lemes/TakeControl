package com.example.TakeControl.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.TakeControl.R
import com.example.TakeControl.UI.adapter.AdapterMovimentacoes
import com.example.TakeControl.UI.model.Movimentacao
import com.github.clans.fab.FloatingActionMenu
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class PrincipalActivity : AppCompatActivity() {

    lateinit var calendarView: MaterialCalendarView
    lateinit var textUsuario: TextView
    lateinit var textSaldo: TextView
    lateinit var recyclerView: RecyclerView
    lateinit var floatActionMenu: FloatingActionMenu


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)
        carregarElementos()
        carregarEventos()
    }

    override fun onRestart() {
        super.onRestart()
        carregarElementos()
        carregarEventos()
    }

    private fun carregarElementos() {
        calendarView = findViewById(R.id.calendarView)
        textSaldo = findViewById(R.id.textSaldo)
        textUsuario = findViewById(R.id.textUsuario)
        recyclerView = findViewById(R.id.recyclerPrincipal)
        floatActionMenu  = findViewById(R.id.floatingActionMenu)
    }

    private fun carregarEventos() {
        configuraCalendarView()
        atualizaRecycler()
        atualizaSaldo()
        dataAtual()
    }

    fun adicionarReceita(view: View){
        val intent = Intent(this,ReceitasActivity::class.java)
        startActivity(intent)
        floatActionMenu.close(true)
    }

    fun adicionarDespesa(view: View){
        val intent = Intent(this,DespesasActivity::class.java)
        startActivity(intent)
        floatActionMenu.close(true)
    }

    fun configuraCalendarView(){
        val meses = arrayOf("Janeiro", "Fevereiro", "Março","Abril","Maio","Junho", "Julho","Agosto" ,"Setembro", "Outubro", "Novembro", "Dezembro" )
        calendarView.setTitleMonths(meses)
        calendarView.setOnMonthChangedListener(OnMonthChangedListener { widget, date ->
            atualizaRecycler(date.month+1, date.year)
        })
    }

    fun atualizaRecycler(mes: Int, ano: Int){
        val lista: ArrayList<Movimentacao> = ListaGlobal.retornaListaMovimentacao()
        var listaFiltrada : ArrayList<Movimentacao> = ArrayList()
        for ( item in lista){
            var dataSplit = item.data.split("/")
            if (dataSplit[1].toInt() == mes && dataSplit[2].toInt() == ano){
                listaFiltrada.add(item)
            }
        }
        recyclerView.adapter = AdapterMovimentacoes(listaFiltrada, this)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    fun atualizaRecycler(){
        val lista: ArrayList<Movimentacao> = ListaGlobal.retornaListaMovimentacao()
        recyclerView.adapter = AdapterMovimentacoes(lista, this)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    fun atualizaSaldo(){
        var soma = 0.0
        var lista: ArrayList<Movimentacao> = ListaGlobal.retornaListaMovimentacao()
        for (item in lista) {
            soma += item.valor
        }
        val country = "BR"
        val language = "pt"
        val somaFormatada = NumberFormat.getCurrencyInstance(Locale(language, country)).format(soma)

        textSaldo.text  = somaFormatada
    }

    fun dataAtual(){
        var datateste = calendarView.currentDate
        atualizaRecycler(datateste.month+1, datateste.year)
    }
}
