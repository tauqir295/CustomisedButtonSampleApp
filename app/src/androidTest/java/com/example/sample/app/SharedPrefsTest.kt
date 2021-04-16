package com.example.sample.app

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.sample.app.utils.HAS_USER_SIGNED_UP
import com.example.sample.app.utils.SHARED_PREF_NAME
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SharedPrefsTest {

    lateinit var sharedPreferences: SharedPreferences

    @Before
    fun setUp() {
        val appContext = ApplicationProvider.getApplicationContext<Context>()
        val masterKey = MasterKey.Builder(appContext, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

        sharedPreferences = EncryptedSharedPreferences.create(
            appContext,
            SHARED_PREF_NAME,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    @Test
    fun writeAndRead() {
        sharedPreferences.edit().apply {
            putBoolean(HAS_USER_SIGNED_UP, true)
        }.apply()

        val hasUserSignedUp = sharedPreferences.getBoolean(HAS_USER_SIGNED_UP, false)

        Assert.assertTrue(hasUserSignedUp)
    }
}