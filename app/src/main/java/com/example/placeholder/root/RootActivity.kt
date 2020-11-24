package com.example.placeholder.root

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.placeholder.R
import com.example.placeholder.home.ui.HomeFragment
import com.example.placeholder.utils.popBackStack
import com.example.placeholder.utils.replace

class RootActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_root)

        if (savedInstanceState != null) return

        replace(HomeFragment.create())
    }

    override fun onBackPressed() {
        popBackStack()
    }
}