package com.example.aplikasikonsultasidokter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aplikasikonsultasidokter.databinding.RowDokterBinding
import com.example.aplikasikonsultasidokter.model.DokterResponse

class DokterAdapter: RecyclerView.Adapter<DokterAdapter.ViewHolder>() {

    private val listDokter = ArrayList<DokterResponse>()

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    fun setData(data: ArrayList<DokterResponse>){
        listDokter.clear()
        listDokter.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = RowDokterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, pos: Int) {
        holder.bind(listDokter[pos])
    }

    override fun getItemCount() = listDokter.size

    inner class ViewHolder(private val binding: RowDokterBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(user: DokterResponse) {
            binding.root.setOnClickListener {
                onItemClickCallback?.onItemClicked(user)
            }

            binding.apply {
                Glide.with(itemView)
                    .load(user.avatar)
                    .circleCrop()
                    .into(itemProfile)
                itemName.text = user.nama
                itemSpecialist.text = user.spesialis
            }
        }
    }

    interface OnItemClickCallback{
        fun onItemClicked(data: DokterResponse)
    }
}