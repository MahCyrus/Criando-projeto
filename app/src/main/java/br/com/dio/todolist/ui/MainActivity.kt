package br.com.dio.todolist.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.dio.todolist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    //Lazy = vai esperar ser chamado o atributo (adapter) para que ele faça a instanciação do adapter (TaskListAdapter())
    private val adapter by lazy { TaskListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Configuração do RecyclerView
        binding.rvTasks.adapter = adapter //Vai receber o adapter que foi criado


        insertListeners()
    }

    private fun insertListeners() {
        binding.fab.setOnClickListener {
            //Mandar essa Activity chamar a próxima Activity (AddTaskActivity)
            startActivities(Intent(this, AddTaskActivity::class.java))
        }
    }
}