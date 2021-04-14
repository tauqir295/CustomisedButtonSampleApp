package com.example.sample.app.login.ui

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobile.design.lib.CustomizableGenericButton
import com.example.sample.app.database.DatabaseRepository
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

    private val _data = MutableLiveData<String>()
    val data: LiveData<String>
        get() = _data

    fun loginUser(view: View) {

        if ((view as CustomizableGenericButton).buttonState == 2) {
            viewModelScope.launch (Dispatchers.IO) {
                userName?.let { userName ->
                    password?.let { password ->
                        try {

                            val user = repository.getUser(userName, password)

                            if(user != null) {
                                _data.postValue("")
                            }

                        } catch (e:Exception) {

                        }

                    }

                }
            }
        }
    }

    fun onTextChanged(userName: String?, password: String?, view: View) {
        (view as CustomizableGenericButton).apply {
            buttonState = if ((userName?.isNotEmpty() == true && password?.isNotEmpty() == true)) {
                CustomizableGenericButton.BUTTON_STATE_ENABLED
            } else {
                CustomizableGenericButton.BUTTON_STATE_DISABLED
            }
        }
    }
}