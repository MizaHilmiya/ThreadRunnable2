package id.ac.polbeng.mizahilmiya.threadrunnable2

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MyWorker(context: Context, workerParams: WorkerParameters) :
    CoroutineWorker(context, workerParams) {

    private val TAG: String = MyWorker::class.java.name

    companion object {
        const val COUNTER = "counter"
        const val PROGRESS = "progress"
        const val MESSAGE = "message"
    }

    override suspend fun doWork(): Result {
        val counter = inputData.getInt(COUNTER, 0)
        Log.i(TAG, "Start counter...")
        for(i in 1..counter) {
            Log.i(TAG, "Counter-$i")
            setProgress(workDataOf(PROGRESS to i))
            withContext(Dispatchers.IO) {
                Thread.sleep(1000)
            }
        }
        Log.i(TAG, "Finish counter!")
        val outputData = workDataOf(MESSAGE to "Finish counter!")
        return Result.success(outputData)
    }
}