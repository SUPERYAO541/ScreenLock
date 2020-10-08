package com.superyao.screenlock

import android.accessibilityservice.AccessibilityService
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_RECEIVER_FOREGROUND
import android.provider.Settings.ACTION_ACCESSIBILITY_SETTINGS
import android.view.accessibility.AccessibilityEvent

class ScreenLockAccessibilityService : AccessibilityService() {

    companion object {

        private const val ACTION_LOCK_SCREEN = "com.superyao.screenlock.ACTION_LOCK_SCREEN"

        fun lockScreenIntent(context: Context): Intent {
            return Intent(context, ScreenLockAccessibilityService::class.java).apply {
                action = ACTION_LOCK_SCREEN
            }
        }
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent) {}

    override fun onInterrupt() {}

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        if (ACTION_LOCK_SCREEN == intent.action) {
            // try to lock the screen
            if (!performGlobalAction(GLOBAL_ACTION_LOCK_SCREEN)) {
                // failed, open the accessibility settings
                Intent(ACTION_ACCESSIBILITY_SETTINGS).apply {
                    addFlags(FLAG_RECEIVER_FOREGROUND)
                }.run {
                    startActivity(this)
                }
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }
}