package com.example.sample.app.home

import android.os.Bundle
import com.example.mobile.design.lib.CustomizableGenericButton
import com.example.sample.app.R

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<CustomizableGenericButton>(R.id.logoutBtn).run {
            setOnClickListener {
                logout()
            }
        }
    }
}