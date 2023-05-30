package mx.itesm.pachontli

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.firebase.ui.auth.AuthUI
import mx.itesm.pachontli.databinding.ActivityMainBinding
import mx.itesm.pachontli.navigationDrawer.NavigationDrawerActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        registrarEventos()
    }

    private fun registrarEventos() {
        binding.btnLogout.setOnClickListener {
            AuthUI.getInstance().signOut(this).addOnCompleteListener {
                //Se ejecuta cuando la sesión se cerró
                //Para regresar al Login
                val intLogin = Intent(this, LoginActivity::class.java)
                startActivity(intLogin)
                finish()
                println("Sesión cerrada")
            }
            println("cerrando sesión...")
        }


        binding.btnPerro.setOnClickListener {

            val intDrawer = Intent(this, NavigationDrawerActivity::class.java)
            intDrawer.putExtra("tipo","perro")
            startActivity(intDrawer)
        }
    }


}