package lab03.eim.systems.cs.pub.practicaltest01var06

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class VictoryBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val message = intent?.getStringExtra("message")
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}