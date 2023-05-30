package mx.itesm.pachontli.navigationDrawer.ui.Alimentos

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import mx.itesm.pachontli.databinding.FragmentAlimentosBinding


class AlimentosFragment : Fragment() {

    private var _binding: FragmentAlimentosBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val galleryViewModel =
            ViewModelProvider(this).get(AlimentosViewModel::class.java)

        _binding = FragmentAlimentosBinding.inflate(inflater, container, false)
        val root: View = binding.root

        /* Descomentar, daba error
        val textView: TextView = binding.textGallery
        galleryViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }*/
        return root
    }

    override fun onStart() {
        super.onStart()

        //Se lanza la aplicación de google maps y busca 'veterinarias cerca'
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse("geo:0,0?q= Veterinarias")
        }
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}