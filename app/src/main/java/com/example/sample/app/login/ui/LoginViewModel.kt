package com.example.sample.app.login.ui

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
class LoginViewModel @Inject constructor() : ViewModel() {
    var userName: String? = null
    var password: String? = null

    private val _data = MutableLiveData<String>()
    val data: LiveData<String>
        get() = _data

    fun loginUser() {
        Log.d("SignUpViewModel", " $userName $password")
        _data.postValue("Success")
    }

}