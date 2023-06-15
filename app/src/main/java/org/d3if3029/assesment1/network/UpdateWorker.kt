package org.d3if3029.assesment1.network

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

class UpdateWorker (
    context: Context,
    workerParams: WorkerParameters
    ) : CoroutineWorker(context, workerParams) {
        companion object {
            const val WORK_NAME = "updater"
        }
        override suspend fun doWork(): Result {
            Log.d("Worker", "Menjalankan proses background..")
            return Result.success()
        }

    }