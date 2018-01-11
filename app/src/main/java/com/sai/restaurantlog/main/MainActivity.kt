package com.sai.restaurantlog.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.View
import android.widget.Toast
import com.sai.restaurantlog.R
import com.sai.restaurantlog.adapters.LogAdapter
import com.sai.restaurantlog.add_log.AddLogActivity
import com.sai.restaurantlog.data.room.Log
import com.sai.restaurantlog.di.LogApplication
import com.sai.restaurantlog.viewmodel.ListCollectionViewModel
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private val TAG: String = MainActivity::class.java.simpleName

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

        ItemTouchHelper(createHelperCallback()).attachToRecyclerView(log_recycler_view)
    }

    private fun setListData(list: List<Log>?) {
        android.util.Log.d(TAG, "Review list observer called")
        if(list != null) {
            if(list.isEmpty()) {
                toggleListVisibility(false)
                return
            }
            toggleListVisibility(true)

            listOfLogs.clear()

            listOfLogs.addAll(list.toList())

            log_recycler_view.layoutManager = LinearLayoutManager(this)
        }
    }

    private fun toggleListVisibility(listVisible: Boolean) {
        empty_reviews_layout.visibility = if(listVisible) View.GONE else View.VISIBLE
        log_recycler_view.visibility = if(listVisible) View.VISIBLE else View.GONE
    }

    private fun goToAddLog() {
        val intent = Intent(this, AddLogActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    private fun createHelperCallback(): ItemTouchHelper.Callback {
        return object : ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
                                target: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
                val position = viewHolder.adapterPosition
                Completable.fromAction({
                    viewmodel.deleteLogItem(
                            listOfLogs[position]
                    )
                })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            listOfLogs.removeAt(position)
                            adapter.notifyItemRemoved(position)
                            Snackbar.make(log_recycler_view, getString(R.string.str_review_deleted),
                                    Snackbar.LENGTH_LONG).show()
                        })
            }
        }
    }
}
