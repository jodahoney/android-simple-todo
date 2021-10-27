package com.example.simpletodo

import android.hardware.biometrics.BiometricManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    // We don't need to define a model in this app because our model is just a list of strings
    val listOfTasks = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        // 1. Let's detect when the user clicks on the add button
//        findViewById<Button>(R.id.button).setOnClickListener {
//            // code in here is going to be executed when the user clicks on the button
//            Log.i("Caren", "User clicked on button")
//        }

        listOfTasks.add("Do laundry")
        listOfTasks.add("Go for a walk")

        // Look up recycler view in the layout
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        // create adapter passing in the list of tasks
        val adapter = TaskItemAdapter(listOfTasks)

        // attach the adapter to the recycler
        recyclerView.adapter = adapter

        // set layout manager to position the items (there are different types)
        // in this case, we will use a simple linear layout
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}