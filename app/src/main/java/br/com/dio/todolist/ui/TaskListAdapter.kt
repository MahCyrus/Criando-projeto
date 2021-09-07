package br.com.dio.todolist.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.dio.todolist.R
import br.com.dio.todolist.databinding.ItemTaskBinding
import br.com.dio.todolist.model.Task

//Transformar a Task em uma View

class TaskListAdapter : ListAdapter<Task, TaskListAdapter.TaskViewHolder>(DiffCallBack()){

    var listenerEdit : (Task) -> Unit = {}
    var listenerDelete : (Task) -> Unit = {}

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

    inner class TaskViewHolder(
        private val binding: ItemTaskBinding
        ) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: Task) {
            binding.tvTitle.text = item.title
            binding.tvDate.text = "${item.date} ${item.hour}"
            binding.ivMore.setOnClickListener {
                showPopup(item)
            }
        }

        private fun showPopup(item: Task) {
            val ivMore = binding.ivMore
            val popupMenu = PopupMenu(ivMore.context, ivMore)//Passar o contexto e a viwe(imagem)
            popupMenu.menuInflater.inflate(R.menu.popup_menu, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener {
                when(it.itemId){
                    //Fazer verificação
                    R.id.action_edit -> listenerEdit(item)
                    R.id.action_delete -> listenerDelete(item)
                }
                return@setOnMenuItemClickListener true
            }
            popupMenu.show()
        }
    }
}

class DiffCallBack : DiffUtil.ItemCallback<Task>(){
    override fun areItemsTheSame(oldItem: Task, newItem: Task) = oldItem == newItem

    override fun areContentsTheSame(oldItem: Task, newItem: Task) = oldItem.id == newItem.id

}