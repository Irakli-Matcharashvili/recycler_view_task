package com.example.recycler_view_task.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.recycler_view_task.model.CarModel

class RecyclerDiffUtil : DiffUtil.ItemCallback<CarModel>() {
    override fun areItemsTheSame(oldItem: CarModel, newItem: CarModel): Boolean = oldItem === newItem
    override fun areContentsTheSame(oldItem: CarModel, newItem: CarModel): Boolean = oldItem == newItem
}