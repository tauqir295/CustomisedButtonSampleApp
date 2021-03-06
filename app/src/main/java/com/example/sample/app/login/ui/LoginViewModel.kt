package com.example.sample.app.login.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sample.app.database.DatabaseRepository
import com.example.sample.app.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * View model class used for updating the UI
 */
@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: DatabaseRepository) : ViewModel() {
    var userName: String? = null
    var password: String? = null
    var hasDataBeenObservedOnce = false // use this variable to help in observing only once. Helpful in device orientation change.
    private val _userData = MutableLiveData<Resource<String>>()
    val userData: LiveData<Resource<String>>
        get() = _userData

    fun loginUser() {
        viewModelScope.launch(Dispatchers.IO) {
            userName?.let { userName ->
                password?.let { password ->
                    try {
                        repository.getUser(userName, password)?.let {
                            _userData.postValue(Resource.success(it))
                        } ?: _userData.postValue(Resource.error("Failure occurred", null))
                    } catch (e: Exception) {
                        _userData.postValue(Resource.error("Failure occurred", null))
                    }
                }
            }
        }
    }
}