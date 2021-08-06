package br.com.carolchiaradia.lembretedecompras.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import br.com.carolchiaradia.lembretedecompras.models.Produto

@Dao
interface ProdutoDao{

// Lista de Produtos
    @Insert (onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(produto: Produto)

    @Query("SELECT * from tabela_produto ORDER BY nome_produto ASC")
    fun getListDeProdutos() : LiveData<List<Produto>>

    @Query( "DELETE FROM tabela_produto")
    suspend fun deleteAll()

    @Delete
    fun delete(produto: Produto)

}