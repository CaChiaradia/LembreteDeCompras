package br.com.carolchiaradia.lembretedecompras.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import br.com.carolchiaradia.lembretedecompras.dao.LembretedeComprasRoomDatabase
import br.com.carolchiaradia.lembretedecompras.models.Produto
import br.com.carolchiaradia.lembretedecompras.repository.ProdutoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository : ProdutoRepository
    val produtos : LiveData<List<Produto>>

    init {
        val produtoDao = LembretedeComprasRoomDatabase.getDatabase(application).produtoDao()
        repository = ProdutoRepository(produtoDao)
        produtos = repository.produtos
    }

    fun insert(produto: Produto) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(produto)

    }

    fun apagarTodos() = viewModelScope.launch(Dispatchers.IO) {
        repository.apagarTodos()
    }
    fun apagar(produto: Produto) = viewModelScope.launch(Dispatchers.IO) {
        repository.apagar(produto)
    }
}
