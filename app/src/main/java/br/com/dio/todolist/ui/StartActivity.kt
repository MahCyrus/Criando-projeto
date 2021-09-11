package br.com.dio.todolist.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.dio.todolist.R

class StartActivity : AppCompatActivity() {
    private val SPLASH_TIME_OUT:Long = 3000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
    }
}