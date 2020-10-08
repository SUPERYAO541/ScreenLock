package com.superyao.screenlock

import android.app.Activity
import android.os.Bundle

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startService(ScreenLockAccessibilityService.lockScreenIntent(this))
        finish()
    }
}