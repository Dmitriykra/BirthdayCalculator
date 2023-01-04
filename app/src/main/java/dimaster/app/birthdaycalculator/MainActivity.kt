package dimaster.app.birthdaycalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var selected_date_tv : TextView? = null
    var minutes_result_tv: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker: Button = findViewById(R.id.select_date_btn)
        selected_date_tv = findViewById(R.id.selected_date_tv)
        minutes_result_tv = findViewById(R.id.minutes_result_tv)

        btnDatePicker.setOnClickListener {
            clickDatePicker()
        }
    }
    private fun clickDatePicker(){
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val dialog = DatePickerDialog(this,
            {
                _, selectedYear, selectedMonth, selectedDayOfMonth ->
                val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
                //? - bcs textview is nullable
                selected_date_tv?.text = selectedDate

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate = sdf.parse(selectedDate)
                theDate?.let {
                    //val selectedDateInMinutes = theDate.time/60000
                    val selectedDateInMinutes = theDate.time/86400000
                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

                    currentDate?.let {
                        //val currentDateInMinutes = currentDate.time / 60000
                        val currentDateInMinutes = currentDate.time / 86400000
                        val differenceInMin = currentDateInMinutes -selectedDateInMinutes

                        minutes_result_tv?.text = differenceInMin.toString()
                    }
                }
            }, year, month, day)

        dialog.datePicker.maxDate = System.currentTimeMillis() - 86400000
            dialog.show()

    }
}