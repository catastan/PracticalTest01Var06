package lab03.eim.systems.cs.pub.practicaltest01var06

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private var scor = 0
    private var serviceStarted = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val number1: EditText = findViewById(R.id.number1)
        val number2: EditText = findViewById(R.id.number2)
        val number3: EditText = findViewById(R.id.number3)
        val checkbox1: CheckBox = findViewById(R.id.checkbox1)
        val checkbox2: CheckBox = findViewById(R.id.checkbox2)
        val checkbox3: CheckBox = findViewById(R.id.checkbox3)
        val playButton: Button = findViewById(R.id.playButton)
        val navigateButton: Button = findViewById(R.id.navigateButton)

        playButton.setOnClickListener {
            val randomValues = List(3) { generateRandomValue() }

            if (!checkbox1.isChecked) {
                number1.setText(randomValues[0])
            }
            if (!checkbox2.isChecked) {
                number2.setText(randomValues[1])
            }
            if (!checkbox3.isChecked) {
                number3.setText(randomValues[2])
            }

            Toast.makeText(this, "Generated numbers: ${randomValues.joinToString(", ")}", Toast.LENGTH_SHORT).show()
        }

        navigateButton.setOnClickListener {
            val numbers = arrayOf(number1.text.toString(), number2.text.toString(), number3.text.toString())
            val checkedCount = listOf(checkbox1, checkbox2, checkbox3).count { it.isChecked }

            val intent = Intent(this, PracticalTest01Var06SecondaryActivity::class.java).apply {
                putExtra("numbers", numbers)
                putExtra("checkedCount", checkedCount)
            }
            startActivityForResult(intent, 1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            val gain = data?.getIntExtra("gain", 0) ?: 0
            scor += gain
            Toast.makeText(this, "Current score: $scor", Toast.LENGTH_SHORT).show()

            if (scor > 0 && !serviceStarted) {
                val serviceIntent = Intent(this, PracticalTest01Var06Service::class.java).apply {
                    putExtra("score", scor)
                }
                startService(serviceIntent)
                serviceStarted = true
            }

            if (scor > 300) {
                val serviceIntent = Intent(this, PracticalTest01Var06Service::class.java).apply {
                    putExtra("score", scor)
                }
                startService(serviceIntent)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (serviceStarted) {
            stopService(Intent(this, PracticalTest01Var06Service::class.java))
        }
    }

    private fun generateRandomValue(): String {
        val values = listOf("1", "2", "3", "*")
        return values[Random.nextInt(values.size)]
    }
}