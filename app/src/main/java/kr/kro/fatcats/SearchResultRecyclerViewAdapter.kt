package kr.kro.fatcats

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kr.kro.fatcats.databinding.ItemMainListBinding
import kr.kro.fatcats.model.AddressDataList

class SearchResultRecyclerViewAdapter : RecyclerView.Adapter<SearchResultRecyclerViewAdapter.ViewHolder>() {

    private var items : List<AddressDataList> = listOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMainListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item,View.OnClickListener{
            Toast.makeText(it.context,"아이템클릭",Toast.LENGTH_SHORT).show()
        })
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun additem(item : List<AddressDataList>){
        items = item
        notifyDataSetChanged()
    }


    inner class ViewHolder (private val binding : ItemMainListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: AddressDataList, listener: View.OnClickListener) = with(binding){
            this.data = data
            itemView.setOnClickListener(listener)
        }

    }
}