package com.example.TakeControl.UI
import android.app.Application
import com.example.TakeControl.UI.model.Movimentacao


class ListaGlobal : Application() {

    companion object{
        var arrayMovimentacaoGlobal: ArrayList<Movimentacao> = ArrayList()

        fun adicionaMovimentacao(item: Movimentacao){
            arrayMovimentacaoGlobal.add(item)
        }

        fun retornaListaMovimentacao() : ArrayList<Movimentacao> {
            return arrayMovimentacaoGlobal
        }

        fun removeMovimentacao(item: Movimentacao){
            arrayMovimentacaoGlobal.remove(item)
        }
    }
}