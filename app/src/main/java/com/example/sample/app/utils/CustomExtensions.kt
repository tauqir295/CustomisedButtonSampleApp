package com.example.sample.app.utils

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.sample.app.R

/**
 * using extension function to extend a class with new functionality.
 * Basically, an extension function is a member function of a class
 * that is defined outside the class.
 *
 * extension method for navigating to other fragment
 */
fun removeSelfAndReplaceWithNextFragment(
    containerID: Int,
    fragmentManager: FragmentManager?,
    fragmentToBeRemoved: Fragment,
    fragmentToBeAdded: Fragment,
    arguments: Bundle?,
    addToBackStack: Boolean = true
) {
    arguments.let {
        fragmentToBeAdded.arguments = it
    }

    fragmentManager?.let {
        it.beginTransaction().apply {
            replace(containerID, fragmentToBeAdded)
            if (addToBackStack) {
                addToBackStack(fragmentToBeAdded::class.simpleName)
            }
            remove(fragmentToBeRemoved)
            commit()
        }
    }
}

/**
 * handling failed case by showing alert dialog
 */
fun showGenericErrorMessage(activity: AppCompatActivity?) {
    activity?.run {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.failure_occurred))
            .setMessage(getString(R.string.something_went_wrong))
            .setPositiveButton(getString(R.string.ok)) { dialog, _ ->
                dialog.dismiss()
            }
            .setCancelable(false)
            .show()
    }
}
