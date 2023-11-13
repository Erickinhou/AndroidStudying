package br.tutorial.dobcalc

import android.app.DatePickerDialog
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private var newFragment: DatePickerFragment? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        newFragment = DatePickerFragment()
    }



    fun showDatePickerDialog(v: View) {
        newFragment?.dateTime = findViewById(R.id.tvDateText)
        newFragment?.dateMinutes = findViewById(R.id.minutesText)
        newFragment?.show(supportFragmentManager, "datePicker")

    }


}



class DatePickerFragment: DialogFragment(), DatePickerDialog.OnDateSetListener {

    var dateTime: TextView? = null
    var dateMinutes: TextView? = null


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(requireContext(), this, year, month, day)
        datePickerDialog.datePicker.maxDate = System.currentTimeMillis() - 86400000
        return datePickerDialog
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
        val selectedDate =  "$day/${month + 1}/$year"
        dateTime?.text = selectedDate
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

        val theDate = sdf.parse(selectedDate)

        val selectedDateInMinutes = theDate.time / 60000

        val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

        val currentDateInMinutes = currentDate.time / 60000

        val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes

        dateMinutes?.text = differenceInMinutes.toString()

//        val minutesText = view.findViewById<TextView>(R.id.minutesText)
    }


}
