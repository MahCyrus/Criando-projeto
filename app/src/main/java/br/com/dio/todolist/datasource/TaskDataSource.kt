package br.com.dio.todolist.datasource

import br.com.dio.todolist.model.Task

object TaskDataSource {
    private val list = arrayListOf<Task>()

    //Pegar a lista
    fun getList() = list

    //Inserir na lista e receber a task que quer inserir por parametro
    fun insertTask(task: Task){
        //Definir o id dessa lista
        //Copy = definir um atributo
        list.add(task.copy(id = list.size + 1))
    }
}