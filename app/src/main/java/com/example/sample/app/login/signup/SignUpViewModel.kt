package com.example.sample.app.login.signup

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * View model class used for updating the UI
 */
@HiltViewModel
class SignUpViewModel @Inject constructor() : ViewModel() {
    var fullName: String? = null
    var userName: String? = null
    var password: String? = null

    private val _data = MutableLiveData<String>()
    val data: LiveData<String>
        get() = _data

    fun registerUser() {
        Log.d("SignUpViewModel", "$fullName $userName $password")
        _data.postValue("Success")
    }

}