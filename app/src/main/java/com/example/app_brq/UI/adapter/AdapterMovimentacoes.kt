package com.example.app_brq.UI.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app_brq.R
import com.example.app_brq.UI.adapter.viewholder.ViewHolderMovimentacao
import com.example.app_brq.UI.model.Movimentacao
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class AdapterMovimentacoes(val arrayDeMovimentacao: ArrayList<Movimentacao>, val context: Context):
        RecyclerView.Adapter<ViewHolderMovimentacao>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMovimentacao {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.lista_movimentacoes, parent,false)
        return ViewHolderMovimentacao(view)
    }

    override fun onBindViewHolder(holder: ViewHolderMovimentacao, position: Int) {
        arrayDeMovimentacao[position].apply {

            val country = "BR"
            val language = "pt"
            val saldoFormatado = NumberFormat.getCurrencyInstance(Locale(language, country)).format(this.valor)

            holder.textViewValor.text = saldoFormatado
            holder.textViewData.text = this.data.toString()
            holder.textViewCategoria.text = this.categoria
            holder.textViewDescricao.text = this.descricao
        }
    }

    override fun getItemCount(): Int {
        return arrayDeMovimentacao.size
    }


}