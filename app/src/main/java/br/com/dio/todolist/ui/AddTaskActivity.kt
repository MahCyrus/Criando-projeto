package br.com.dio.todolist.ui

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import br.com.dio.todolist.databinding.ActivityAddTaskBinding
import br.com.dio.todolist.datasource.TaskDataSource
import br.com.dio.todolist.extensions.format
import br.com.dio.todolist.extensions.text
import br.com.dio.todolist.model.Task
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.util.*

//Declarar a activity no AndroidManifest
class AddTaskActivity : AppCompatActivity() {

    //lateinit = inicializar depois

    private lateinit var binding: ActivityAddTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    //ViewBinding = pega os xml e cria as classes para eles
        //Referenciar o xml. Inflate = inflar. .val no final = joga em uma variavel
        binding = ActivityAddTaskBinding.inflate(layoutInflater) //.val
        // .root = para usar o layout do objeto de vinculação
        setContentView(binding.root)

        //Para escolher uma data
        insertListeners()
    }

    private fun insertListeners() {
        binding.tilDate.editText?.setOnClickListener{
            /*Testar
            Log.e("TAG", "insertListeners: ", )*/
            //Inserir o calendario
            val datePicker = MaterialDatePicker.Builder.datePicker().build()
            //Inserir a data
            datePicker.addOnPositiveButtonClickListener {
                //Para a data vir correta. Vai pegar o padrao que esta no dispositivo
                val timeZone = TimeZone.getDefault()
                val offset = timeZone.getOffset(Date().time) * -1
                //Instanciar uma nova data
                binding.tilDate.text = Date(it + offset).format()
            }
            //Para aparecer na tela. Tag = id
            datePicker.show(supportFragmentManager, "DATE_PICKER_TAG")
        }

        //Se o editText estiver nulo, não executa a função
        binding.tilHour.editText?.setOnClickListener {
            val timePicker = MaterialTimePicker.Builder().setTimeFormat(TimeFormat.CLOCK_24H).build()
            timePicker.addOnPositiveButtonClickListener {
                val hour = if (timePicker.hour in 0..9) "0${timePicker.hour}" else timePicker.hour
                val minute = if (timePicker.minute in 0..9) "0${timePicker.minute}" else timePicker.minute

                binding.tilHour.text = "$hour:$minute"
            }
            timePicker.show(supportFragmentManager, null)
        }

        binding.btnCancel.setOnClickListener {
            finish()
        }

        binding.btnNewTask.setOnClickListener {
            //Criar uma nova task
            val task = Task(
                title = binding.tilTitle.text,
                descricao = binding.tilDescricao.text,
                date =  binding.tilDate.text,
                hour = binding.tilHour.text
            )
            TaskDataSource.insertTask(task)
            /*Log.e("TAG", "insertListeners: " + TaskDataSource.getList() )*/
            setResult(Activity.RESULT_OK)
            finish()
        }

    }
}