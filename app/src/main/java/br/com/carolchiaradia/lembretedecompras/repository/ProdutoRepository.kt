package br.com.carolchiaradia.lembretedecompras.repository

import androidx.lifecycle.LiveData
import br.com.carolchiaradia.lembretedecompras.dao.ProdutoDao
import br.com.carolchiaradia.lembretedecompras.models.Produto

class ProdutoRepository (private val produtoDao: ProdutoDao) {

    val produtos: LiveData<List<Produto>> = produtoDao.getListDeProdutos()
        suspend fun insert(produto: Produto){
                produtoDao.insert(produto)
            }
    suspend fun apagarTodos(){
        produtoDao.deleteAll()
    }
    suspend fun apagar(produto: Produto){
        produtoDao.delete(produto)
    }


}