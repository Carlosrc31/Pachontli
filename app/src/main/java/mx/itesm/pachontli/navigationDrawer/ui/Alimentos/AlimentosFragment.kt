package mx.itesm.pachontli.navigationDrawer.ui.Alimentos

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import mx.itesm.pachontli.ListaAlimentosActivity
import mx.itesm.pachontli.databinding.FragmentAlimentosBinding

class AlimentosFragment : Fragment() {

    private var _binding: FragmentAlimentosBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    //
//    private var animal:  String = ""
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(AlimentosViewModel::class.java)

        _binding = FragmentAlimentosBinding.inflate(inflater, container, false)
        val root: View = binding.root



        //Pasar animal a Alimentos
        //val animal = intent.getStringExtra("tipo")
        //println("El tipo es $animal")



        registrarEventos()
        return root
    }

    /*fun setAnimal(animal: String){
        this.animal = animal
    }*/
    private fun registrarEventos() {


        var animal = ""
        var preferencias = requireActivity().getSharedPreferences("datos", MODE_PRIVATE)
        animal = preferencias?.getString("animal", "") !!


       // val animal = arguments?.getString("animal")
        println("recibe animal en alim fragment: $animal")

        //val animal = intent.getStringExtra("tipo")
        //println("El tipo es $animal")

        binding.btnNocivos.setOnClickListener {
            val intentListaAli = Intent(context, ListaAlimentosActivity::class.java)
            intentListaAli.putExtra("alimento","Nocivos")
            intentListaAli.putExtra("animal", "$animal")
            startActivity(intentListaAli)
        }

        binding.btnRecomendables.setOnClickListener {
            val intentListaAli = Intent(context, ListaAlimentosActivity::class.java)
            intentListaAli.putExtra("alimento","Recomendables")
            intentListaAli.putExtra("animal", "$animal")
            startActivity(intentListaAli)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}