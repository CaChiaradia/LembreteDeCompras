package br.com.carolchiaradia.lembretedecompras.novoproduto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.carolchiaradia.lembretedecompras.R
import br.com.carolchiaradia.lembretedecompras.databinding.ActivityMainBinding
import br.com.carolchiaradia.lembretedecompras.databinding.ActivityNovoProdutoBinding

class NovoProdutoActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_PRODUTO = "EXTRA_PRODUTO"
    }

    private lateinit var binding: ActivityNovoProdutoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNovoProdutoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btSalvar.setOnClickListener {
            val respostaIntent = Intent()
            val produto = binding.etProduto.text.toString()
            if(produto.isEmpty()) {
                setResult(RESULT_CANCELED, respostaIntent)
            } else {
                respostaIntent.putExtra(EXTRA_PRODUTO, produto)
                setResult(RESULT_OK, respostaIntent)
            }

            finish()
        }
    }
}