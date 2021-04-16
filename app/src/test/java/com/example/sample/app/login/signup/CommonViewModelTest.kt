package com.example.sample.app.login.signup

import android.content.SharedPreferences
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.sample.app.TestCoroutineRule
import com.example.sample.app.database.DatabaseRepository
import com.example.sample.app.database.UserDao
import com.example.sample.app.login.ui.LoginViewModel
import com.example.sample.app.observeOnce
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

/**
 * Test cases for view model.
 */
@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CommonViewModelTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var userDao: UserDao
    private lateinit var signUpViewModel: SignUpViewModel
    private lateinit var loginViewModel: LoginViewModel

    private lateinit var repository: DatabaseRepository

    @Before
    @Throws(Exception::class)
    fun before() {
        repository = DatabaseRepository(userDao)
        val sharedPrefs = Mockito.mock(SharedPreferences::class.java)

        signUpViewModel = SignUpViewModel(repository, sharedPrefs).apply {
            userName = "test"
            password = "password"
            fullName = "full name"
        }

        loginViewModel = LoginViewModel(repository).apply {
            userName = "test"
            password = "password"
        }
    }

    @Test
    fun registerUser() {
        testCoroutineRule.runBlockingTest {
            signUpViewModel.registerUser()
            signUpViewModel.signedUpData.observeOnce {
                assertEquals("Data inserted successfully.", it.data)
            }
        }
    }

    @Test
    fun loginUser() {
        testCoroutineRule.runBlockingTest {
            loginViewModel.run {
                loginUser()

                userData.observeOnce {
                    assertNull(it.data)
                }
            }

        }
    }
}