package com.sai.restaurantlog.add_log

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.sai.restaurantlog.R

class AddLogActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_log)

        title = getString(R.string.str_add_new_log)
    }
}
