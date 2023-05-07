package mx.itesm.pachontli

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth
import mx.itesm.pachontli.databinding.ActivityLoginBinding
import mx.itesm.pachontli.view.MainActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding


    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ){ result ->
        this.onSignInResult(result)
    }

    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult?) {
        if(result?.resultCode == RESULT_OK){
            val usuario = FirebaseAuth.getInstance().currentUser
            println("Welcome ${usuario?.displayName}")
            println("Email ${usuario?.email}")
            println("Token ${usuario?.uid}")

            //Carga siguiente pantalla
            pantallaEntrada()

        }else{
            println("Error de autenticación")

        }
    }

    private fun pantallaEntrada() {
        val intMenu = Intent(this, MainActivity::class.java)
        startActivity(intMenu)
        finish() //Destruye la pantalla de Login
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_login)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        registrarEventos()
    }

    //Ayuda a dejar que el usuario permamezca en la sesión hasta que se cierre la sesión
    override fun onStart() {
        super.onStart()

        val usuario = FirebaseAuth.getInstance().currentUser

        if(usuario != null){
            pantallaEntrada()
        }
    }

    private fun registrarEventos() {
        binding.btnLogin.setOnClickListener {
            autenticar()
        }
    }

    //Función para autenticar con google para LogIn
    private fun autenticar(){
        val providers = arrayListOf(
            AuthUI.IdpConfig.GoogleBuilder().build(),
        )

        val signInIntent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .build()
        signInLauncher.launch(signInIntent)
    }

}