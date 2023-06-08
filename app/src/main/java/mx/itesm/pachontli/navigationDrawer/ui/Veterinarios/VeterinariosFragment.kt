package mx.itesm.pachontli.navigationDrawer.ui.Veterinarios

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import mx.itesm.pachontli.databinding.FragmentAlimentosBinding
import mx.itesm.pachontli.databinding.FragmentVeterinariosBinding


class VeterinariosFragment : Fragment() {

    private var _binding: FragmentVeterinariosBinding? = null

    var firstTime = true

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val galleryViewModel =
            ViewModelProvider(this).get(VeterinariosViewModel::class.java)

        _binding = FragmentVeterinariosBinding.inflate(inflater, container, false)
        val root: View = binding.root

        registrarEventos()
        return root
    }

    private fun registrarEventos() {
        binding.btnGoogleMaps.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("geo:0,0?q= Veterinarias")
            }
            startActivity(intent)
        }
    }

    /*override fun onStart() {
        super.onStart()

        //Se lanza la aplicación de google maps y busca 'veterinarias cerca'

        if(firstTime) {
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("geo:0,0?q= Veterinarias")
            }
            startActivity(intent)

            firstTime = false
        }
    }*/

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}