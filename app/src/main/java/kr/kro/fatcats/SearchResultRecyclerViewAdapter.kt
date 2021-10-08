package kr.kro.fatcats

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import kr.kro.fatcats.databinding.ItemMainListBinding
import kr.kro.fatcats.model.AddressDataList

class SearchResultRecyclerViewAdapter : RecyclerView.Adapter<SearchResultRecyclerViewAdapter.ViewHolder>() {

    private var items : List<AddressDataList> = listOf()
    lateinit var onClick : (AddressDataList) -> Unit


    class ViewHolder (private val binding : ItemMainListBinding,val onClick:(AddressDataList)->Unit) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: AddressDataList) = with(binding){
            this.data = data
            binding.root.setOnClickListener {
                onClick(data)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMainListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding,onClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.apply {
            bind(item)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
    fun additem(item : List<AddressDataList>,onClick:(AddressDataList) -> Unit){
        items = item
        this.onClick = onClick
        notifyDataSetChanged()
    }
}