package com.example.TakeControl.UI.model
import com.example.TakeControl.UI.ListaGlobal
import java.io.Serializable

class Movimentacao(
        val valor: Double,
        val data: String,
        val categoria: String,
        val descricao: String,
        val tipoMovimentacao: String
): Serializable {

    fun adicionaMovimentacao(){
        ListaGlobal.adicionaMovimentacao(this)
    }
}