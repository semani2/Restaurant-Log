package com.sai.restaurantlog.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.sai.restaurantlog.R
import com.sai.restaurantlog.adapters.LogAdapter
import com.sai.restaurantlog.add_log.AddLogActivity
import com.sai.restaurantlog.data.room.Log
import com.sai.restaurantlog.di.LogApplication
import com.sai.restaurantlog.viewmodel.ListCollectionViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private val listOfLogs: MutableList<Log> = mutableListOf()

    @Inject lateinit var viewmodelFactory: ViewModelProvider.Factory

    private lateinit var viewmodel: ListCollectionViewModel
    private lateinit var adapter: LogAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as LogApplication).getApplicationComponent().inject(this)

        viewmodel = ViewModelProviders.of(this, viewmodelFactory)
                .get(ListCollectionViewModel::class.java)

        viewmodel.getListItems().observe(this, Observer<List<Log>> {
            list -> setListData(list)
        })

        initRecyclerView()


        add_log_fab.setOnClickListener({
            goToAddLog()
        })
    }

    private fun initRecyclerView() {
        adapter = LogAdapter(this, listOfLogs)
        log_recycler_view.adapter = adapter
    }

    private fun setListData(list: List<Log>?) {
        if(list != null) {
            listOfLogs.clear()

            listOfLogs.addAll(list.toList())

            log_recycler_view.layoutManager = LinearLayoutManager(this)
        }
    }

    private fun goToAddLog() {
        val intent = Intent(this, AddLogActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }
}
