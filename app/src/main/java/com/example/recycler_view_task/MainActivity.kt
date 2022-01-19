package com.example.recycler_view_task

import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recycler_view_task.adapter.RecyclerViewAdapter
import com.example.recycler_view_task.adapter.listener.ItemClickListener
import com.example.recycler_view_task.databinding.ActivityMainBinding
import com.example.recycler_view_task.model.CarModel

class MainActivity : AppCompatActivity(), ItemClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var myAdapter: RecyclerViewAdapter
    private val myList = mutableListOf<CarModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        myAdapter = RecyclerViewAdapter(this)
        with(binding.mainRecyclerView) {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = myAdapter
        }
        addNewCarButtonListener()
    }

    private fun addNewCarButtonListener() {
        binding.addNewCarButton.setOnClickListener {
            checkFields()
        }
    }

    private fun checkFields() {
        with(binding) {
            if (manufacturerEditTextView.text.toString()
                    .isNotBlank() && modelEditTextView.text.toString()
                    .isNotBlank() && yearEditTextView.text.toString().isNotBlank()
            ) {
                addItem()
            } else {
                val emptyField = emptyItem(manufacturerEditTextView, modelEditTextView)
                Toast.makeText(this@MainActivity, emptyField, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun emptyItem(manufacturer: EditText, model: EditText): String {
        return when {
            manufacturer.text.toString().isBlank() -> {
                getString(R.string.empty_manufacturer_text)
            }
            model.text.toString().isBlank() -> { getString(R.string.empty_model_text)}
            else -> {
                getString(R.string.empty_year_text)
            }
        }
    }

    private fun addItem() {
        with(binding) {
            val newList = mutableListOf<CarModel>()
            val item = CarModel(
                manufacturerEditTextView.text.toString(),
                modelEditTextView.text.toString(),
                yearEditTextView.text.toString().toInt()
            )
            myList.add(item)
            newList.addAll(myList)
            myAdapter.submitList(newList)
        }
    }

    override fun onItemClick(car: CarModel) {
        Toast.makeText(
            this,
            "${car.manufacturer} ${car.model} ${car.year} " + getString(R.string.app_car_year_string_text),
            Toast.LENGTH_SHORT
        )
            .show()
    }

    override fun deleteItem(position: Int) {
        val newList = mutableListOf<CarModel>()
        myList.removeAt(position)
        newList.addAll(myList)
        myAdapter.submitList(newList)
    }
}