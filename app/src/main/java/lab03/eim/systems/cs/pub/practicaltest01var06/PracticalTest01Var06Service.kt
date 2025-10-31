package lab03.eim.systems.cs.pub.practicaltest01var06

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

class PracticalTest01Var06Service : Service() {

    private val handler = Handler()

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("PracticalTest01Var06Service", "Service started")
        val score = intent?.getIntExtra("score", 0) ?: 0
        if (score > 300) {
            handler.postDelayed({
                val currentTime = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
                val broadcastIntent = Intent("victory").apply {
                    putExtra("message", "Victory! Score: $score at $currentTime")
                }
                sendBroadcast(broadcastIntent)
            }, 2000)
        }
        return START_REDELIVER_INTENT
    }
}