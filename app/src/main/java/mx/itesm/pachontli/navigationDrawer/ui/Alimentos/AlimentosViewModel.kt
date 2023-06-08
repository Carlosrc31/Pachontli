package mx.itesm.pachontli.navigationDrawer.ui.Alimentos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AlimentosViewModel : ViewModel() {

   /* private val aliFrag = AlimentosFragment()
    val data = MutableLiveData<String>()

    fun setData(newData: String){
        data.value = aliFrag.setAnimal(newData).toString()
    }*/

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
}