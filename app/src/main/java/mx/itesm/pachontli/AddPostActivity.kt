package mx.itesm.pachontli

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import mx.itesm.pachontli.databinding.ActivityAddPostBinding
import java.util.Date

class AddPostActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddPostBinding

    //Obtenemos al usuario (uid)
    val usuario = FirebaseAuth.getInstance()
    //Para accede a la db
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        registrarEventos()
    }

    private fun registrarEventos() {
        binding.btnPublicar.setOnClickListener {
            val postText = binding.etPost.text.toString()
            val user = usuario.currentUser?.displayName
            val fecha = Date()

            val post = Post(user, fecha, postText)

            db.collection("interactuaPosts").add(post)
                .addOnSuccessListener {
                    finish()
                }
                .addOnFailureListener {
                    AlertDialog.Builder(this).apply{
                        setTitle("Error")
                        setMessage(it.message.toString())
                        setPositiveButton("Aceptar", null)
                    }.show()
                }

        }
    }


}