package vcmsa.ci.financetracker
import android.graphics.Color
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val incomeInput = findViewById<EditText>(R.id.incomeInput)
        val expenseFood = findViewById<EditText>(R.id.expenseFood)
        val expenseEntertainment = findViewById<EditText>(R.id.expenseEntertainment)
        val expenseBills = findViewById<EditText>(R.id.expenseBills)
        val expenseMisc = findViewById<EditText>(R.id.expenseMiscellaneous)

        val calculateButton = findViewById<Button>(R.id.calculateButton)

        val outputIncome = findViewById<TextView>(R.id.outputTotalIncome)
        val outputExpenses = findViewById<TextView>(R.id.outputTotalExpenses)
        val outputBalance = findViewById<TextView>(R.id.outputBalance)
        val outputFeedback = findViewById<TextView>(R.id.outputFeedback)
        calculateButton.setOnClickListener {
            try {
                val income = incomeInput.text.toString().toDoubleOrNull()
                val food = expenseFood.text.toString().toDoubleOrNull()
                val entertainment = expenseEntertainment.text.toString().toDoubleOrNull()
                val bills = expenseBills.text.toString().toDoubleOrNull()
                val misc = expenseMisc.text.toString().toDoubleOrNull()

                if (income == null || food == null || entertainment == null || bills == null || misc == null) {
                    Toast.makeText(this, "Please enter valid numbers for all fields.", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                val totalExpenses = food + entertainment + bills + misc
                val balance = income - totalExpenses

                outputIncome.text = "Total Income: R%.2f".format(income)
                outputExpenses.text = "Total Expenses: R%.2f".format(totalExpenses)
                outputBalance.text = "Balance: R%.2f".format(balance)

                // Show saving or overspending message
                if (balance >= 0) {
                    outputFeedback.text = "You are saving money!"
                    outputFeedback.setTextColor(Color.GREEN)
                } else {
                    outputFeedback.text = "You are overspending!"
                    outputFeedback.setTextColor(Color.RED)
                }

            } catch (e: Exception) {
                Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }
}
