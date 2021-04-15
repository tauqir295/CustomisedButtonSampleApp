package com.example.sample.app

import com.example.sample.app.utils.isValidPassword
import org.junit.Assert
import org.junit.Test

class PasswordTest {

    @Test
    fun testPassword() {
        Assert.assertFalse("".isValidPassword())
        Assert.assertFalse("ANDROID@123".isValidPassword())
        Assert.assertFalse("android@123".isValidPassword())
        Assert.assertFalse("androids".isValidPassword())
        Assert.assertFalse("android123".isValidPassword())
        Assert.assertFalse("123456789".isValidPassword())
        Assert.assertFalse("@#\$%^&+=".isValidPassword())
        Assert.assertFalse("App$1e".isValidPassword())

        Assert.assertTrue("passW@r4".isValidPassword())
        Assert.assertTrue("Android@123".isValidPassword())
    }

}