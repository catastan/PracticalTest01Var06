package lab03.eim.systems.cs.pub.practicaltest01var06

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PracticalTest01Var06SecondaryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_secondary)

        val numbers = intent.getStringArrayExtra("numbers")
        val checkedCount = intent.getIntExtra("checkedCount", 0)

        val resultTextView: TextView = findViewById(R.id.resultTextView)
        val gainTextView: TextView = findViewById(R.id.gainTextView)

        if (numbers != null && areNumbersEqual(numbers)) {
            resultTextView.text = "Gained"
        } else {
            resultTextView.text = "Not Gained"
        }

        val gain = when (checkedCount) {
            0 -> 100
            1 -> 50
            2 -> 10
            else -> 0
        }
        gainTextView.text = "Gain: $gain"

        val returnIntent = Intent()
        returnIntent.putExtra("gain", gain)
        setResult(Activity.RESULT_OK, returnIntent)
    }


    private fun areNumbersEqual(numbers: Array<String>): Boolean {
        val distinctNumbers = numbers.filter { it != "*" }.distinct()
        return distinctNumbers.size <= 1
    }
}
