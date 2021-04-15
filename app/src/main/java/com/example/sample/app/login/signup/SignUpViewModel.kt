package com.example.sample.app.login.signup

import android.content.SharedPreferences
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobile.design.lib.CustomizableGenericButton
import com.example.mobile.design.lib.CustomizableGenericButton.Companion.BUTTON_STATE_DISABLED
import com.example.mobile.design.lib.CustomizableGenericButton.Companion.BUTTON_STATE_ENABLED
import com.example.sample.app.database.DatabaseRepository
import com.example.sample.app.database.model.User
import com.example.sample.app.utils.HAS_USER_SIGNED_UP
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

    private val _data = MutableLiveData<String>()
    val data: LiveData<String>
        get() = _data

    fun registerUser(view: View) {

        if ((view as CustomizableGenericButton).buttonState == 2) {
            viewModelScope.launch {
                fullName?.let { fullName ->
                    userName?.let { userName ->
                        password?.let { password
                            val user = User(fullName = fullName, userName = userName, password = password )
                            repository.insert(user = user)

                            _data.postValue("")

                            sharedPreferences.edit().apply {
                                putBoolean(HAS_USER_SIGNED_UP, true)
                            }.apply()
                        }

                    }
                }
            }
        }
    }

    fun onTextChanged(fullName: String?, userName: String?, password: String?, view: View) {
        (view as CustomizableGenericButton).apply {
            buttonState = if ((fullName?.isNotEmpty() == true && userName?.isNotEmpty() == true && password?.isNotEmpty() == true)) {
                BUTTON_STATE_ENABLED
            } else {
                BUTTON_STATE_DISABLED
            }
        }
    }
}