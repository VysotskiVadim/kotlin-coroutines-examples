package dev.vadzimv.kotlincoroutinesexamples

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class TextButtonFragment : Fragment() {

    protected fun setTitle(title: CharSequence) {
        (activity as? MainActivity)?.supportActionBar?.title = title
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.text_and_button, container, false)
}