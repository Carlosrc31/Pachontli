package mx.itesm.pachontli.navigationDrawer

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.AttributeSet
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.firebase.ui.auth.AuthUI
import com.google.android.material.navigation.NavigationView
import mx.itesm.pachontli.LoginActivity
import mx.itesm.pachontli.R
import mx.itesm.pachontli.databinding.ActivityNavigationDrawerBinding
import mx.itesm.pachontli.navigationDrawer.ui.Alimentos.AlimentosFragment
import mx.itesm.pachontli.navigationDrawer.ui.Alimentos.AlimentosViewModel

class NavigationDrawerActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityNavigationDrawerBinding

    //private val alimentosVM: AlimentosViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNavigationDrawerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarNavigationDrawer.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_navigation_drawer)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_alimentos, R.id.nav_veterinarios, R.id.nav_interactua
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val animal = intent.getStringExtra("animal")
        println("El tipo es $animal en NavDrawer")

        val animalToAlimento = animal
        val preferencias = getSharedPreferences("datos", MODE_PRIVATE)
        preferencias.edit {
            putString("animal", animalToAlimento)
            commit()
        }

       /* if (animal != null) {
            alimentosVM.setData(animal)
        }*/

        //Falta pasar info entre está activity a el fragment de navigation drawer
       /* val fragment = AlimentosFragment()
        val bundle = Bundle()
        bundle.putString("animal", "$animal")
        fragment.arguments = bundle
        supportFragmentManager.beginTransaction().add(R.id.drawer_layout, fragment).commit()*/


        //Pasar animal a Alimentos

       /* val intAlimentos = Intent(this, AlimentosFragment::class.java)
        intAlimentos.putExtra("animal","$animal")
        startActivity(intAlimentos)*/

    }

    /*override fun onStart() {
        super.onStart()
        val animal = intent.getStringExtra("animal")
        println("El tipo es $animal en NavDrawer")

        //Pasar animal a Alimentos
        val fragment = AlimentosFragment()
        val bundle = Bundle()
        bundle.putString("animal", "$animal")
        fragment.arguments = bundle
        supportFragmentManager.beginTransaction().add(R.id.drawer_layout, fragment).commit()
    }*/


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.navigation_drawer, menu)
        return true
    }

    //override fun onOptionsItemSelected(item: MenuItem): Boolean {

    //Falta de arreglar para cerrar sesión cuando corresponde
    //println("Click en logout")
    //return false
    /* AuthUI.getInstance().signOut(this).addOnCompleteListener {
         //Se ejecuta cuando la sesión se cerró
         //Para regresar al Login
         val intLogin = Intent(this, LoginActivity::class.java)
         startActivity(intLogin)
         finish()
         println("Sesión cerrada")
     }
     println("cerrando sesión...")
     return super.onOptionsItemSelected(item)*/
    //}

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.cerrarSesion ->{
                println("Cerrando sesión ${item.itemId}")
                AuthUI.getInstance().signOut(this).addOnCompleteListener {
                    //Se ejecuta cuando la sesión se cerró
                    //Para regresar al Login
                    val intLogin = Intent(this, LoginActivity::class.java)
                    startActivity(intLogin)
                    finish()
                    println("Sesión cerrada")
                }
                println("cerrando sesión...")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_navigation_drawer)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}