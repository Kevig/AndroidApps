package com.example.kevig.ibudget

import android.app.DatePickerDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import java.text.SimpleDateFormat
import java.util.*

class NewTransactionActivity : AppCompatActivity() {

    private lateinit var setTransactionDateButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_transaction)

        // Set and initialise Date picker component
        this.setTransactionDateButton = this.findViewById(R.id.setTransactionDate_Button)
        this.transactionDateComponent()

    }

    // Date Picker component
    private fun transactionDateComponent() {
        var calender: Calendar = Calendar.getInstance()

        // Listen for updates to Date picker's date and set transaction date text to match
        val dateListener: DatePickerDialog.OnDateSetListener = DatePickerDialog.OnDateSetListener {
                                                                    _, year, monthOfYear, dayOfMonth ->
            calender.set(Calendar.YEAR, year)
            calender.set(Calendar.MONTH, monthOfYear)
            calender.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val dateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH)
            this.setTransactionDateButton.text = dateFormat.format(calender.time)
        }

        // Listen for click event and create Date Picker
        this.setTransactionDateButton.setOnClickListener {
            DatePickerDialog(this@NewTransactionActivity, dateListener,
                calender.get(Calendar.YEAR),
                calender.get(Calendar.MONTH),
                calender.get(Calendar.DAY_OF_MONTH)).show()
        }
    }

    fun onAddItemToTransaction(view: View) {

    }
}

