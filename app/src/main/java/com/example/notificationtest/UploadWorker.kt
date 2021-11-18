package com.example.notificationtest

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters

class UploadWorker(appContext: Context, workerParams: WorkerParameters):
    Worker(appContext, workerParams){

    private var cnt = 0

    override fun doWork(): Result {

        val textTitle = "テスト"
        val textContent = "テスト通知"
        val notificationId = 1

        Log.d("test","通知")

        val intent = Intent(applicationContext, SubActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent: PendingIntent =
            PendingIntent.getActivity(applicationContext, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        val builder = NotificationCompat.Builder(applicationContext, "213412")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(textTitle)
            .setContentText(textContent)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        fun popNotification() {
            with(NotificationManagerCompat.from(applicationContext)) {
                notify(notificationId, builder.build())
            }
        }
        popNotification()
        // Indicate whether the work finished successfully with the Result
        return Result.success()
    }

}

