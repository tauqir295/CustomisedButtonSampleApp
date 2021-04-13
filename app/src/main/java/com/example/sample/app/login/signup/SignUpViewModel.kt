package com.example.sample.app.login.signup

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mobile.design.lib.CustomizableGenericButton
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * View model class used for updating the UI
 */
@HiltViewModel
class SignUpViewModel @Inject constructor(private val repository: SignUpRepository) : ViewModel() {
    var fullName: String? = null
    var userName: String? = null
    var password: String? = null

    private val _data = MutableLiveData<String>()
    val data: LiveData<String>
        get() = _data

    fun registerUser(view: View) {

    }

    fun onTextChanged(fullName: String?, userName: String?, password: String?, view: View) {

        println("$fullName $userName $password")
        var buttonState = 0
        buttonState = if (fullName?.isNotEmpty() == true && userName?.isNotEmpty() == true && password?.isNotEmpty() == true) {
            1
        } else {
            0
        }

        (view as CustomizableGenericButton).setButtonState(buttonState)

    }
}