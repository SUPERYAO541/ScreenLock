package com.superyao.screenlock

import android.accessibilityservice.AccessibilityService
import android.content.Context
import android.content.Intent
import android.view.accessibility.AccessibilityEvent
import android.widget.Toast

class ScreenLockService : AccessibilityService() {

    companion object {
        fun start(context: Context) = context.startService(
            Intent(context, ScreenLockService::class.java)
        )
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent) {}

    override fun onInterrupt() {}

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int =
        try {
            if (!performGlobalAction(GLOBAL_ACTION_LOCK_SCREEN)) {
                // failed, open the accessibility settings
                startActivity(
                    Intent(android.provider.Settings.ACTION_ACCESSIBILITY_SETTINGS).apply {
                        addFlags(Intent.FLAG_RECEIVER_FOREGROUND)
                    }
                )
            }
            super.onStartCommand(intent, flags, startId)
        } catch (e: Exception) {
            Toast.makeText(
                this,
                "${getString(R.string.exception)} ${e.message}",
                Toast.LENGTH_LONG
            ).show()
            super.onStartCommand(intent, flags, startId)
        }
}