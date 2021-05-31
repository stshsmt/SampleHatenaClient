package com.websarva.wings.android.samplehatenaclient.index

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("bookmarkCount")
fun TextView.setBookmarkCount(count: Int) {
    text = "$count users"
}

@BindingAdapter("pageUrl")
fun ImageView.setPageUrl(url: String?) {
    url?.let {
        Glide.with(context).load(url).into(this)
    }
}