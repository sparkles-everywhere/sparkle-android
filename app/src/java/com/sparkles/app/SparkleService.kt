package com.sparkles.app

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.PixelFormat
import android.os.Build
import android.os.IBinder
import android.view.Gravity
import android.view.WindowManager
import android.widget.Toast
import androidx.core.app.NotificationCompat

class SparkleService : Service() {
    private var windowManager: WindowManager? = null
    private var sparkleView: SparkleView? = null
    private var isOverlayActive = false

    companion object {
        private const val NOTIFICATION_ID = 1
        private const val CHANNEL_ID = "sparkles_channel"
        private const val ACTION_STOP = "com.sparkles.app.ACTION_STOP"

        fun startService(context: Context) {
            val intent = Intent(context, SparkleService::class.java)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context.startForegroundService(intent)
            } else {
                context.startService(intent)
            }
        }

        fun stopService(context: Context) {
            val intent = Intent(context, SparkleService::class.java)
            context.stopService(intent)
        }
    }

    override fun onCreate() {
        super.onCreate()
        SparkleConfig.load(this)
        createNotificationChannel()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            ACTION_STOP -> {
                stopOverlay()
                stopSelf()
                return START_NOT_STICKY
            }
            else -> {
                startForeground()
                startOverlay()
            }
        }
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onDestroy() {
        super.onDestroy()
        stopOverlay()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Sparkles Overlay",
                NotificationManager.IMPORTANCE_LOW
            ).apply {
                description = "Sparkles overlay notification"
            }

            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun startForeground() {
        val notification = createNotification()
        startForeground(NOTIFICATION_ID, notification)
    }

    private fun createNotification(): Notification {
        val openAppIntent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        val openAppPendingIntent = android.app.PendingIntent.getActivity(
            this,
            1,
            openAppIntent,
            android.app.PendingIntent.FLAG_IMMUTABLE or
                android.app.PendingIntent.FLAG_UPDATE_CURRENT
        )

        val stopIntent = Intent(this, SparkleService::class.java).apply {
            action = ACTION_STOP
        }

        val stopPendingIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            android.app.PendingIntent.getService(
                this,
                0,
                stopIntent,
                android.app.PendingIntent.FLAG_IMMUTABLE or android.app.PendingIntent.FLAG_UPDATE_CURRENT
            )
        } else {
            android.app.PendingIntent.getService(
                this,
                0,
                stopIntent,
                android.app.PendingIntent.FLAG_UPDATE_CURRENT
            )
        }

        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Sparkles Active")
            .setContentText("Tap to open Sparkles")
            .setSmallIcon(android.R.drawable.ic_menu_view)
            .setOngoing(true)
            .setContentIntent(openAppPendingIntent)
            .addAction(
                android.R.drawable.ic_menu_close_clear_cancel,
                "Stop",
                stopPendingIntent
            )
            .build()
    }

    private fun startOverlay() {
        if (isOverlayActive) return

        if (!canDrawOverlays()) {
            Toast.makeText(
                this,
                "Overlay permission not granted",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        sparkleView = SparkleView(this)

        val layoutParams = WindowManager.LayoutParams(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT,
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
            } else {
                @Suppress("DEPRECATION")
                WindowManager.LayoutParams.TYPE_PHONE
            },
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE or
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL or
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            PixelFormat.TRANSLUCENT
        )

        layoutParams.gravity = Gravity.TOP or Gravity.START
        layoutParams.alpha = 1f
        layoutParams.dimAmount = 0f

        try {
            windowManager?.addView(sparkleView, layoutParams)
            sparkleView?.start()
            isOverlayActive = true
        } catch (e: Exception) {
            Toast.makeText(
                this,
                "Failed to create overlay: ${e.message}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun stopOverlay() {
        if (!isOverlayActive) return

        try {
            sparkleView?.stop()
            windowManager?.removeView(sparkleView)
            isOverlayActive = false
        } catch (e: Exception) {
            // View might already be removed
        } finally {
            sparkleView = null
            windowManager = null
        }
    }

    private fun canDrawOverlays(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            android.provider.Settings.canDrawOverlays(this)
        } else {
            true
        }
    }
}
