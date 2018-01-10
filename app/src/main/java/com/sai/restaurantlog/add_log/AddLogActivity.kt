package com.sai.restaurantlog.add_log

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.RatingBar
import com.sai.restaurantlog.R
import com.sai.restaurantlog.data.room.Log
import com.sai.restaurantlog.di.LogApplication
import com.sai.restaurantlog.main.MainActivity
import com.sai.restaurantlog.viewmodel.NewLogViewModel
import kotlinx.android.synthetic.main.activity_add_log.*
import javax.inject.Inject

class AddLogActivity : AppCompatActivity() {

    @Inject lateinit var viewmodelFactory: ViewModelProvider.Factory

    private lateinit var newLogViewModel : NewLogViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_log)

        title = getString(R.string.str_add_new_log)

        (application as LogApplication).getApplicationComponent().inject(this)

        newLogViewModel =  ViewModelProviders.of(this, viewmodelFactory)
                .get(NewLogViewModel::class.java)

        save_button.setOnClickListener {
            if (restaurant_name_text_view.isEmpty()) {
                restaurant_name_text_view.error = "Please enter a valid restaurant name"
                return@setOnClickListener
            }

            if (restaurant_review_text_view.isEmpty()) {
                restaurant_review_text_view.error = "Please enter a valid review"
                return@setOnClickListener
            }

            var rating: Float = if (restaurant_rating_bar.isValidRating()) {
                restaurant_rating_bar.rating
            } else {
                0F
            }

            var log = Log(restaurantName = restaurant_name_text_view.getString(),
                    review = restaurant_review_text_view.getString(), rating = rating, id = 0)

            newLogViewModel.addNewLogItem(log)

            goToMain()
        }
    }

    private fun goToMain() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }
}

private fun EditText.isEmpty(): Boolean = this.text.isEmpty()
private fun EditText.getString(): String = this.text.toString()

private fun RatingBar.isValidRating(): Boolean = this.rating.isFinite()