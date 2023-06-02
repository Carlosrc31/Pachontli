package mx.itesm.pachontli

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import mx.itesm.pachontli.navigationDrawer.ui.Interactuar.InteractuarFragment
import java.text.SimpleDateFormat

class PostAdapter(private val contexto: InteractuarFragment, private val dataset: List<Post> ) :RecyclerView.Adapter<PostAdapter.templatePost>() {

    //Obtenemos al usuario (uid)
    val usuario = FirebaseAuth.getInstance()
    //Para accede a la db
    private val db = FirebaseFirestore.getInstance()



    //Diseña el viewHolder que muestra la info de cada card
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): templatePost {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.template_post, parent, false)
        return templatePost(view)
    }

    //Número de renglones a generar
    override fun getItemCount() = dataset.size

    //Pone los datos de la posición 'position' en el componente 'holder'
    override fun onBindViewHolder(holder: templatePost, position: Int) {
        val post = dataset[position]

        //Para funcionamiento de likes
        var likes = post.likes!!.toMutableList()
        //Recuerda el uid del user que da like
        var liked = likes.contains(usuario.uid)

        isLiked(liked, holder.btn_like)

        //Para controla que se quite o se matenga el like (se niega el liked)
        holder.btn_like.setOnClickListener {
            liked = !liked
            isLiked(liked, holder.btn_like)

            if(liked) {
                likes.add(usuario.uid!!)
            }
            else{
                likes.remove(usuario.uid)
            }

            val doc = db.collection("interactuaPosts").document(post.uid!!)

            //Para visualizar la actualización de likes en tiempo real
            db.runTransaction {
                it.update(doc, "likes", likes)

                null
            }

        }

        //Le envía al template post los datos del índice correspondiente, además del número de likes
        holder.set(post, likes.size)
    }


    class templatePost(var viewPost: View): RecyclerView.ViewHolder(viewPost) {

        val btn_like = viewPost.findViewById<Button>(R.id.btn_like)

        fun set(post: Post, likes: Int){
            val sdf = SimpleDateFormat("dd/M/yyyy hh:mm a")
            viewPost.findViewById<TextView>(R.id.tv_username).text = post.username
            viewPost.findViewById<TextView>(R.id.tv_date).text = sdf.format(post.date)
            viewPost.findViewById<TextView>(R.id.tv_post).text = post.post
            if(likes == 1){
                viewPost.findViewById<TextView>(R.id.tv_numLikes).text = "${likes} like"
            }
            else{
                viewPost.findViewById<TextView>(R.id.tv_numLikes).text = "${likes} likes"
            }
        }
    }

    //Cambia el color del btn likes para saber si está seleccionado o no
    private fun isLiked(liked: Boolean, btn_like: Button){
        if(liked) btn_like.setTextColor(ContextCompat.getColor(contexto.requireContext(), R.color.teal_200 ))
        else btn_like.setTextColor(Color.BLACK)
    }

}