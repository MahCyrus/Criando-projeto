package br.com.dio.todolist.datasource

import br.com.dio.todolist.model.Task

object TaskDataSource {
    private val list = arrayListOf<Task>()

    //toList = Criar uma referencia da lista
    //Pegar a lista
    fun getList() = list.toList()

    //Inserir na lista e receber a task que quer inserir por parametro
    fun insertTask(task: Task){
        if (task.id == 0){
            //Nova task
            list.add(task.copy(id = list.size + 1))
        }else{
            list.remove(task)
            list.add(task)
        }
        //Definir o id dessa lista
        //Copy = definir um atributo
        list.add(task.copy(id = list.size + 1))
    }

    fun findById(taskId: Int) = list.find { it.id == taskId }//Comparação em toda a lista

    fun deleteTask(task: Task) {
        list.remove(task)
    }
}