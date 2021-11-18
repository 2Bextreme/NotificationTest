package com.example.notificationtest

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.work.*
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.concurrent.schedule

class MainActivity : AppCompatActivity() {

    companion object {
        private const val CHANNEL_ID = "213412"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val uploadWorkRequest: WorkRequest =
            PeriodicWorkRequest.Builder(UploadWorker::class.java,16,TimeUnit.MINUTES).build()

        createNotificationChannel()
        WorkManager
            .getInstance(applicationContext)
            .enqueue(uploadWorkRequest)

        val button: Button = findViewById(R.id.notificationBtn)
        button.setOnClickListener {
//            val test: EditText = findViewById(R.id.editTextDate2)
        }
    }
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "テストチャンネル"
            val descriptionText = "テスト通知"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}

