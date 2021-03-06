package br.com.carolchiaradia.lembretedecompras.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.carolchiaradia.lembretedecompras.R
import br.com.carolchiaradia.lembretedecompras.databinding.ActivityLoginBinding
import br.com.carolchiaradia.lembretedecompras.main.MainActivity
import br.com.carolchiaradia.lembretedecompras.models.RequestState
import br.com.carolchiaradia.lembretedecompras.models.Usuario

class LoginActivity : AppCompatActivity() {

    private lateinit var animacaoDoMascote : Animation
    private lateinit var animacaoDoFormulario : Animation

    private lateinit var binding: ActivityLoginBinding

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        binding = ActivityLoginBinding.inflate(layoutInflater)

        setContentView(binding.root)

        iniciarAnimacao()
        esconderTeclado()

        iniciarListener()

        iniciarViewModel()
        iniciarObserver()

        loginViewModel.getUsuarioLogado()

    }

    private fun iniciarViewModel(){
        loginViewModel = ViewModelProvider (this).get(LoginViewModel::class.java)
    }

    private fun iniciarObserver(){
        loginViewModel.usuarioLogadoState.observe(this, Observer {
            when(it){
                is RequestState.Success ->
                    binding.etEmail.setText(it.data)
                is RequestState.Error ->{}
                is RequestState.Loading ->{}
            }

        })
        loginViewModel.loginState.observe(this, Observer {
            when(it){
                is RequestState.Success -> {
                    binding.tvPasswordFeedback.text = ""
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }

                is RequestState.Error ->{
                    binding.tvPasswordFeedback.text= it.throwable.message

                }
                is RequestState ->{
                    Log.i("LEMBRETECOMPRAS", "CARREGANDO")
                }
            }

        })

    }


    private fun iniciarListener(){
        binding.etPassword.setOnFocusChangeListener{ v, hasFocus ->
            if(hasFocus){
                binding.ivLogin.speed = 2f
                binding.ivLogin.setMinAndMaxProgress(0.0f, 0.65f)
            } else{
                binding.ivLogin.speed = 1f
                binding.ivLogin.setMinAndMaxProgress(0.65f, 1.0f)

            }
            binding.ivLogin.playAnimation()
        }

        binding.btLogin.setOnClickListener {
            loginViewModel.logar(
                Usuario(
                    binding.etEmail.text.toString(),
                    binding.etPassword.text.toString()
                )
            )
        }
    }

    private fun esconderTeclado() {
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
    }

    private fun iniciarAnimacao(){
        animacaoDoMascote = AnimationUtils.loadAnimation(this, R.anim.animacao_formulario)
        animacaoDoFormulario = AnimationUtils.loadAnimation(this, R.anim.animacao_mascote)

        binding.ivLogin.clearAnimation()
        binding.containerLogin.clearAnimation()

        binding.ivLogin.startAnimation(animacaoDoMascote)
        binding.containerLogin.startAnimation(animacaoDoFormulario)


    }
}