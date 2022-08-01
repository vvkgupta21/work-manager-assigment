package personal.project.workmanagerassignment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import personal.project.workmanagerassignment.databinding.ItemDataBinding
import personal.project.workmanagerassignment.room.PostModel

class AdapterRoom(  val list : ArrayList<PostModel>): RecyclerView.Adapter<AdapterRoom.Holder>() {

    class Holder(val binding: ItemDataBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        Holder(ItemDataBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val model = list[position]
        holder.binding.model = model
    }

    override fun getItemCount(): Int = list.size
}