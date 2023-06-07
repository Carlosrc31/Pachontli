package mx.itesm.pachontli.navigationDrawer.ui.Interactuar

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import mx.itesm.pachontli.AddPostActivity
import mx.itesm.pachontli.Post
import mx.itesm.pachontli.PostAdapter
import mx.itesm.pachontli.databinding.FragmentInteractuarBinding
import java.util.*


class InteractuarFragment : Fragment() {

    private var _binding: FragmentInteractuarBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    //variable del adaptador
    var adaptador: PostAdapter? = null

    //variable para conexión a firebase
    private val db = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        val slideshowViewModel =
            ViewModelProvider(this).get(InteractuarViewModel::class.java)

        _binding = FragmentInteractuarBinding.inflate(inflater, container, false)
        val root: View = binding.root

        configurarAdaptador()
        registrarEventos()

        return root

    }

    private fun configurarAdaptador() {

        //Para verificar los cambios en db en tiempo real
        db.collection("interactuaPosts").addSnapshotListener { value, error ->
            val posts = value!!.toObjects(Post::class.java)

            posts.forEachIndexed { index, post ->
                post.uid = value.documents[index].id

                //Layout manager
                val layout = LinearLayoutManager(context)
                layout.orientation = LinearLayoutManager.VERTICAL
                binding.rvPosts.layoutManager = layout

                //Adaptador
                adaptador = PostAdapter(this, posts)

                //Conecta adaptador al RecyclerView
                binding.rvPosts.adapter = adaptador

            }

        }
    }

    private fun registrarEventos(){
        binding.fabCreatePost.setOnClickListener {
            //When you click the button it redirect you to the screen to create a new post
            val intent = Intent(context, AddPostActivity::class.java)
            startActivity(intent)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}