package mx.itesm.pachontli

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListaAlimentosAdapter(private val context: Context, private val dataAlimentos: List<AlimentoItem>) : RecyclerView.Adapter<ListaAlimentosAdapter.templateTipoAlimento>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): templateTipoAlimento {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.template_tipo_alimento, parent, false)
        return templateTipoAlimento(view)
    }


    override fun getItemCount() = dataAlimentos.size

    override fun onBindViewHolder(holder: templateTipoAlimento, position: Int) {
        val alimentoItem = dataAlimentos.get(position)

        holder.set(alimentoItem)
    }

    class templateTipoAlimento(var viewAlimentoItem: View): RecyclerView.ViewHolder(viewAlimentoItem){
        fun set(alimentoItem: AlimentoItem){
            viewAlimentoItem.findViewById<TextView>(R.id.tv_nombreAli).text = alimentoItem.nombreAli
            viewAlimentoItem.findViewById<TextView>(R.id.tv_categoAli).text = alimentoItem.cateAli
            viewAlimentoItem.findViewById<TextView>(R.id.tv_estadoAli).text = alimentoItem.estadoAli
            viewAlimentoItem.findViewById<TextView>(R.id.tv_descripcionAli).text = alimentoItem.descripcionAli
        }
    }

}