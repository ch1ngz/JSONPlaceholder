package com.example.placeholder.home.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.fragment.app.Fragment
import com.example.placeholder.R
import com.example.placeholder.home.model.PostType
import com.example.placeholder.utils.hideKeyboard
import com.example.placeholder.utils.replace
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_home.loadingTextView
import kotlinx.android.synthetic.main.fragment_home.postEditText
import kotlinx.android.synthetic.main.fragment_home.sendButton
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(R.layout.fragment_home) {

    companion object {
        fun create() = HomeFragment()
    }

    private val viewModel: HomeViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sendButton.setOnClickListener { send() }

        viewModel.postLiveData.observe(viewLifecycleOwner) { post ->
            when (post) {
                is PostType.Filled ->
                    replace(DetailsFragment.create(post.title, post.body))
                is PostType.NotFilled ->
                    Toast.makeText(requireContext(), R.string.home_invalid_post, Toast.LENGTH_SHORT).show()
                is PostType.Error ->
                    Snackbar.make(view, R.string.error_general_message, Snackbar.LENGTH_SHORT).show()
            }
        }

        viewModel.loadingLiveData.observe(viewLifecycleOwner) { isLoading ->
            loadingTextView.isInvisible = !isLoading
            sendButton.isEnabled = !isLoading
        }
    }

    private fun send() {
        postEditText.hideKeyboard()

        val postId = postEditText.text.toString().toLongOrNull()
        if (postId == null) {
            Toast.makeText(requireContext(), R.string.home_invalid_id, Toast.LENGTH_SHORT).show()
            return
        }

        viewModel.check(postId)
    }
}