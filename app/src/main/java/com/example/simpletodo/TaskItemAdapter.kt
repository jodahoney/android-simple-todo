package com.example.simpletodo

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * A bridge that tells the recycler view how to display the data that we give it.
 * The data we give it is the list of tasks(strings) that we define in main acitivity.
 */
class TaskItemAdapter(val listOfItems: List<String>,
                      val longClickListener: onLongClickListener) :
    RecyclerView.Adapter<TaskItemAdapter.ViewHolder>(){

    interface onLongClickListener {
        // interface to be implemented in our main activity
        fun onItemLongClicked(position: Int)
    }


    // Usually involved inflating a layout from XML and returning the holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)
    }

    // Involves populating data into the item through holder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        // get data model based on position
        val item = listOfItems.get(position)

        // set the text view to be whatever this is
        holder.textView.text = item
    }

    override fun getItemCount(): Int {
        return listOfItems.size
    }


    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Store references to elements in our layout view
        val textView: TextView

        init {
            textView = itemView.findViewById(android.R.id.text1)

            itemView.setOnLongClickListener {
                longClickListener.onItemLongClicked(adapterPosition)
                true
            }
        }
    }
}