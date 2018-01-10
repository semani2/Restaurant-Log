package com.sai.restaurantlog.main

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.sai.restaurantlog.R
import com.sai.restaurantlog.add_log.AddLogActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        add_log_fab.setOnClickListener({
            goToAddLog()
        })
    }

    private fun goToAddLog() {
        val intent = Intent(this, AddLogActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }
}
