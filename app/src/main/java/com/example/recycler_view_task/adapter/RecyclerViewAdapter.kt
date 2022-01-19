package com.example.recycler_view_task.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.recycler_view_task.adapter.listener.ItemClickListener
import com.example.recycler_view_task.databinding.RecyclerViewLayoutBinding
import com.example.recycler_view_task.model.CarModel

class RecyclerViewAdapter(private val itemClickListener: ItemClickListener): ListAdapter<CarModel, RecyclerViewAdapter.ViewHolder>(RecyclerDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RecyclerViewLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.onBind(item, itemClickListener)
    }

    class ViewHolder(
        private val binding: RecyclerViewLayoutBinding,
    ):
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(
            car: CarModel,
            itemClickListener: ItemClickListener,
        ) {
            with(binding) {
                manufacturerTextView.text = car.manufacturer
                modelTextView.text = car.model
                yearTextView.text = car.year.toString()
                recyclerViewLayout.setOnClickListener {
                    itemClickListener.onItemClick(car)
                }
                recyclerViewLayout.setOnLongClickListener {
                    itemClickListener.deleteItem(adapterPosition)
                    true
                }
            }
        }
    }
}