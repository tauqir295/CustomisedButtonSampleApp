package com.example.sample.app.login.signup

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sample.app.database.DatabaseRepository
import com.example.sample.app.database.model.User
import com.example.sample.app.utils.HAS_USER_SIGNED_UP
import com.example.sample.app.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * View model class used for updating the UI
 */
@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val repository: DatabaseRepository,
    private val sharedPreferences: SharedPreferences
    ) : ViewModel() {
    var fullName: String? = null
    var userName: String? = null
    var password: String? = null

    private val _signedUpData = MutableLiveData<Resource<String>>()
    val signedUpData: LiveData<Resource<String>>
        get() = _signedUpData

    fun registerUser() {
        viewModelScope.launch {
            val user = User(fullName = fullName, userName = userName, password = password )
            repository.insert(user = user)

            _signedUpData.postValue(Resource.success("Data inserted successfully."))

            sharedPreferences.edit().apply {
                putBoolean(HAS_USER_SIGNED_UP, true)
            }.apply()
        }
    }
}