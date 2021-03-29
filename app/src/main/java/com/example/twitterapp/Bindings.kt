package com.example.twitterapp

import android.content.Intent
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.twitterapp.ui.UserDisplayActivity
import com.example.twitterapp.ui.UserDisplayActivity.Companion.twitterHandleKey

@BindingAdapter("editText", "handle")
fun Button.handleErrorState(editText: EditText, handle: String) {
    setOnClickListener {
        editText.handleErrorState(handle)
        editText.navigateToUserDetailsWhenValid(handle)
    }
}

fun EditText.handleErrorState(handle: String) {
    error = if (handle.isEmpty()) {
        context.getString(R.string.empty_handle_error_message)
    } else {
        null
    }
}

@BindingAdapter("tweetText")
fun TextView.setTweetTextDependingOnUserStatus(tweetText: String?) {
    text = if (tweetText.isNullOrEmpty()) {
        context.getString(R.string.empty_tweet_placeholder_message)
    } else {
        tweetText
    }
}

@BindingAdapter("errorText")
fun ConstraintLayout.showToastOnError(errorMessage: String?) {
    errorMessage?.let {
        Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
    }
}

fun EditText.navigateToUserDetailsWhenValid(handle: String) {
    if (handle.isNotEmpty()) {
        context.startActivity(
            Intent(context, UserDisplayActivity::class.java).putExtra(
                twitterHandleKey,
                handle
            )
        )
    }
}

@BindingAdapter("imageUrl")
fun ImageView.setImageUrl(url: String?) {
    Glide.with(context)
        .load(url)
        .placeholder(R.drawable.twitter)
        .error(R.drawable.twitter)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(this)
}