package br.com.carolchiaradia.lembretedecompras.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.carolchiaradia.lembretedecompras.databinding.ProdutoItemBinding
import br.com.carolchiaradia.lembretedecompras.models.Produto

class MainListAdapter(
    private val deleteListener: (Produto) -> Unit
)
    : RecyclerView.Adapter<MainListAdapter.ProdutoViewHolder>() {

    private var produtos = emptyList<Produto>()

    inner class ProdutoViewHolder(val binding: ProdutoItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdutoViewHolder {
        return ProdutoViewHolder(
            ProdutoItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ProdutoViewHolder, position: Int) {
        val produto = produtos[position]
        holder.binding.tvProduto.text = produto.nome

        holder.binding.ivDelete.setOnClickListener {
            deleteListener.invoke(produto)
        }
    }

    fun setProdutos(produtos: List<Produto>) {
        this.produtos = produtos
        notifyDataSetChanged()
    }

    override fun getItemCount() = produtos.size

}