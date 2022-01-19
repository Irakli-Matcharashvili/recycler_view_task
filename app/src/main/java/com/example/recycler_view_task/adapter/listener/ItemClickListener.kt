package com.example.recycler_view_task.adapter.listener

import com.example.recycler_view_task.model.CarModel

interface ItemClickListener {
    fun onItemClick(car: CarModel)
    fun deleteItem(position: Int)
}