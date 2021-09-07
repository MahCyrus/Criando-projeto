package br.com.dio.todolist.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.dio.todolist.databinding.ItemTaskBinding
import br.com.dio.todolist.model.Task

//Transformar a Task em uma View

class TaskListAdapter : ListAdapter<Task, TaskListAdapter.TaskViewHolder>(DiffCallBack()){

    //Para extender
    //Criar a View
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        //Recuperar layout pelo contexto da view
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTaskBinding.inflate(inflater, parent, false)//Parent para se guiar e false para não precisar ser ataxado ao parent
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(getItem(position))//Pega a lista e a posiçao que passar
    }

    class TaskViewHolder(
        private val binding: ItemTaskBinding
        ) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: Task) {
            binding.tvTitle.text = item.title
            binding.tvDate.text = "${item.date} ${item.hour}"
        }
    }
}

class DiffCallBack : DiffUtil.ItemCallback<Task>(){
    override fun areItemsTheSame(oldItem: Task, newItem: Task) = oldItem == newItem

    override fun areContentsTheSame(oldItem: Task, newItem: Task) = oldItem.id == newItem.id

}