package com.codeliner.draganddrop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var items : ArrayList<String>
    private lateinit var rcViewAdapter: RecyclerViewAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this) //set layout of our recyclerView as linear
        //we need adapter and viewholder for rcView

        items = fetchData()
        rcViewAdapter = RecyclerViewAdapter(items) //set adapter
        recyclerView.adapter =rcViewAdapter
        val itemTouchHelper = ItemTouchHelper(simpleCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    val simpleCallback = object : ItemTouchHelper.SimpleCallback(
        ItemTouchHelper.UP or ItemTouchHelper.DOWN
                or ItemTouchHelper.START or ItemTouchHelper.END, 0) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            val startPosition = viewHolder.adapterPosition //start position
            val endPosition = target.adapterPosition //end position
            Collections.swap(items, startPosition, endPosition)
            rcViewAdapter.notifyItemMoved(startPosition, endPosition)//notify the adapter about item moved
            return false
            //now attach rcView to item touch helper
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            TODO("Not yet implemented")
        }
    }

    //we will fetch data using
    fun fetchData() : ArrayList<String> {
        val list = ArrayList<String>()
        for (i in 0 until 20) {
            list.add("Item $i")
        }
        return list
    }
}