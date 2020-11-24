package com.example.placeholder.utils

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.example.placeholder.R

fun Fragment.popBackStack() {
    requireActivity().popBackStack()
}

fun FragmentActivity.popBackStack() {
    hideKeyboard()

    if (supportFragmentManager.backStackEntryCount < 2) {
        finish()
    } else {
        supportFragmentManager.popBackStackImmediate()
    }
}

fun Fragment.replace(
    target: Fragment,
    @IdRes containerId: Int = R.id.fragment_container,
    addToBackStack: Boolean = true,
    fragmentManager: FragmentManager = requireActivity().supportFragmentManager
) {
    requireActivity().hideKeyboard()
    fragmentManager.commit(allowStateLoss = true) {
        replace(containerId, target, target.javaClass.name)
        if (addToBackStack) addToBackStack(target.javaClass.name)
    }
}

fun FragmentActivity.replace(
    target: Fragment,
    @IdRes containerId: Int = R.id.fragment_container,
    addToBackStack: Boolean = true
) {
    hideKeyboard()
    supportFragmentManager.commit(allowStateLoss = true) {
        replace(containerId, target, target.javaClass.name)
        if (addToBackStack) addToBackStack(target.javaClass.name)
    }
}
