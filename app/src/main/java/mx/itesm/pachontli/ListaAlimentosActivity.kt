package mx.itesm.pachontli

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import mx.itesm.pachontli.databinding.ActivityListaAlimentosBinding

class ListaAlimentosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListaAlimentosBinding


    private lateinit var arrAlimentos: ArrayList<AlimentoItem>
    //var arrAlimentos = arrayListOf<String>()

    var adaptador: ListaAlimentosAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListaAlimentosBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //val aliItem = AlimentoItem("Uva", "Fruta", "Dañino en pocas cantidades", "Incluso en las cantidades más pequeñas pueden provocar fallo renal")

        //val aliItems = listOf(aliItem)

        //println("AliItems: $aliItems")
        /*val layout = LinearLayoutManager(this)
        layout.orientation = LinearLayoutManager.VERTICAL
        binding.rvAlimentos.layoutManager = layout

        adaptador = ListaAlimentosAdapter(this, aliItems)

        binding.rvAlimentos.adapter = adaptador
*/
        //registrarEventos()
    }

    override fun onStart() {
        super.onStart()
        arrAlimentos = arrayListOf()
        //arrAlimentos = newArrayLis
        leerDatos()
    }

    private fun leerDatos() {

        //Se obtiene el dato de la pantalla anteriro para saber si es Alimento Nocivo o Recomendable
        val alimento = intent.getStringExtra("alimento")
        println("El alimento es $alimento en Lista")

        //Se obtiene el dato de la pantalla anteriro para saber si es Alimento Nocivo o Recomendable
        val animal = intent.getStringExtra("animal")
        println("El animal es $animal en Lista")


        val baseDatos = Firebase.database
        val referencia = baseDatos.getReference("/$animal/$alimento")


        //Layout manager
        val layout = LinearLayoutManager(this)
        layout.orientation = LinearLayoutManager.VERTICAL
        binding.rvAlimentos.layoutManager = layout


        //Adaptador
        adaptador = ListaAlimentosAdapter(this, arrAlimentos)

        //Conecta adaptador al RecyclerView
        binding.rvAlimentos.adapter = adaptador

        referencia.addValueEventListener (object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                arrAlimentos.clear()
                for( registro in snapshot.children){
                    println("Re: $registro")
                    val alimento = registro.getValue(AlimentoItem::class.java)
                    println("Ali: $alimento")
                    //arrAlimentos.add("${alimento?.nombreAli} - ${alimento?.cateAli} - ${alimento?.estadoAli} - ${alimento?.descripcionAli}")
                    if (alimento != null) {
                        arrAlimentos.add(alimento)
                    }
                    println("Ali: $alimento")

                }

                //adaptador = ListaAlimentosAdapter(this, arrAlimentos)
                adaptador!!.notifyDataSetChanged()

            }

            override fun onCancelled(error: DatabaseError) {
                println("Operación Cancelada")
            }
        })
    }

    /* private fun registrarEventos() {
         binding.btnRegresar.setOnClickListener {
             val intentAlimentos = Intent(this, NavigationDrawerActivity::class.java)
             startActivity(intentAlimentos)
         }
     }*/
}