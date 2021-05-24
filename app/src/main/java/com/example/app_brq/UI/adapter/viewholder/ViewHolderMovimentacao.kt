package com.example.app_brq.UI.adapter.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.app_brq.R

class ViewHolderMovimentacao(view: View): RecyclerView.ViewHolder(view) {

    var textViewValor: TextView
    var textViewData: TextView
    var textViewCategoria: TextView
    var textViewDescricao: TextView

    init {

        textViewValor = view.findViewById(R.id.textViewValor)
        textViewData = view.findViewById(R.id.textViewData)
        textViewCategoria = view.findViewById(R.id.textViewCategoria)
        textViewDescricao = view.findViewById(R.id.textViewDescricao)

    }

}