package dev.vadzimv.kotlincoroutinesexamples

import androidx.fragment.app.Fragment

fun Fragment.setActivityTitle(title: CharSequence) {
    (activity as? MainActivity)?.supportActionBar?.title = title
}