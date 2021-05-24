package com.example.app_brq.UI

import android.content.Intent
import android.icu.text.Collator.getDisplayName
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
            //atualizaRecyclerTeste(date.month)

        })
    }
//
//    fun atualizaRecyclerTeste(mes: Int){
//        val mes = calendario.get(Calendar.MONTH)
//        val lista: ArrayList<Movimentacao> = ListaGlobal.retornaListaMovimentacao()
//        val listaFiltradaPorMes = lista.filter { it. }
//        recyclerView.adapter = AdapterMovimentacoes(lista, this)
//        recyclerView.layoutManager = LinearLayoutManager(this)
//    }

    fun atualizaRecycler(){
        val lista: ArrayList<Movimentacao> = ListaGlobal.retornaListaMovimentacao()
        recyclerView.adapter = AdapterMovimentacoes(lista, this)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    fun atualizaSaldo(){
        var soma = 0.0
        var lista: ArrayList<Movimentacao> = ListaGlobal.retornaListaMovimentacao()
        for (item in lista) {
            soma = soma + item.valor
        }
        val country = "BR"
        val language = "pt"
        val somaFormatada = NumberFormat.getCurrencyInstance(Locale(language, country)).format(soma)

        textSaldo.text  = somaFormatada

    }
}
