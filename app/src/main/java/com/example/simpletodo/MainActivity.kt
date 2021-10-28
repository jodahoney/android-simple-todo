package com.example.simpletodo

import android.hardware.biometrics.BiometricManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.FileUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.apache.commons.io.FileUtils.readLines
import java.io.File
import java.io.IOException
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {

    // We don't need to define a model in this app because our model is just a list of strings
    var listOfTasks = mutableListOf<String>()
    lateinit var adapter: TaskItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val onLongClickListener = object : TaskItemAdapter.onLongClickListener {
            override fun onItemLongClicked(position: Int) {
                // 1. Remove item from the list
                listOfTasks.removeAt(position)
                // 2. Notify the adapter that our data set has changed
                adapter.notifyDataSetChanged()

                saveItems()
            }
        }

        loadItems()

        // Look up recycler view in the layout
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        // create adapter passing in the list of tasks
        adapter = TaskItemAdapter(listOfTasks, onLongClickListener)
        // attach the adapter to the recycler
        recyclerView.adapter = adapter
        // set layout manager to position the items (there are different types)
        // in this case, we will use a simple linear layout
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Set up the button and input field so user can enter task to list

        val inputTextField = findViewById<EditText>(R.id.addTaskField)

        // Grab reference to button and set onclicklistener
        findViewById<Button>(R.id.button).setOnClickListener {
            // 1. grab the text that the user has input into the addTaskField
            val userInputtedTask = inputTextField.text.toString()

            // 2. Add the string to the list of tasks
            listOfTasks.add(userInputtedTask)

            // notify the adapter that our data has been updated
            adapter.notifyItemInserted(listOfTasks.size - 1)

            // 3. Clear the addTask field of text
            inputTextField.setText("")

            saveItems()
        }
    }

    // Save the data that the user has input
    // Save data by writing and reading from a file

    // Create a method to get the file we need
    fun getDataFile() : File {
        // every line is going to represent a specific task in our list of tasks
        return File(filesDir, "data.txt")
    }

    // Load the items by reading every line in the data file
    fun loadItems() {
        try {
            listOfTasks = org.apache.commons.io.FileUtils.readLines(getDataFile(), Charset.defaultCharset())
        } catch (ioException: IOException) {
            ioException.printStackTrace()
        }
    }

    // Save items by writing them into our data file
    fun saveItems() {
        try {
            org.apache.commons.io.FileUtils.writeLines(getDataFile(), listOfTasks)
        } catch (ioException: IOException) {
            ioException.printStackTrace()
        }

    }

}