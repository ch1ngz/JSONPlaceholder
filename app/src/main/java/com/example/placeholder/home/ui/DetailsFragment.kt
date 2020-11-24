package com.example.placeholder.home.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.placeholder.R
import com.example.placeholder.utils.popBackStack
import kotlinx.android.synthetic.main.fragment_details.bodyTextView
import kotlinx.android.synthetic.main.fragment_details.titleTextView
import kotlinx.android.synthetic.main.fragment_details.toolbar

class DetailsFragment : Fragment(R.layout.fragment_details) {

    companion object {
        private const val EXTRA_TITLE = "EXTRA_TITLE"
        private const val EXTRA_BODY = "EXTRA_BODY"

        fun create(title: String, body: String): DetailsFragment {
            val fragment = DetailsFragment()

            val bundle = Bundle()
            bundle.putString(EXTRA_TITLE, title)
            bundle.putString(EXTRA_BODY, body)

            fragment.arguments = bundle
            return fragment
        }
    }

    private val title: String by lazy { requireArguments().getString(EXTRA_TITLE).orEmpty() }
    private val body: String by lazy { requireArguments().getString(EXTRA_BODY).orEmpty() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar.setNavigationOnClickListener { popBackStack() }
        titleTextView.text = title
        bodyTextView.text = body
    }
}