package com.example.kevig.tbudget;

import android.app.DatePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

class ComponentDatePicker {

    private Context context;
    private TextView textView;

    ComponentDatePicker(Context c, TextView v) {
        this.context = c;
        this.textView = v;

        Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateListener = this.dateChangeListener(calendar);
        this.onClickDatePicker(calendar, dateListener);
    }

    /**
     * Initialises a date change listener component for use in a date picker dialog
     * @param calendar instance
     * @return Date Change Listener instance
     */
    private DatePickerDialog.OnDateSetListener dateChangeListener(final Calendar calendar) {

        return new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy",
                                                                    Locale.ENGLISH);

                textView.setText(dateFormat.format(calendar.getTime()));
            }
        };
    }

    /**
     * On click event listener for 'Set Date' TextView, opens a date picker dialog
     * @param calendar instance
     * @param dateListener Date change listener
     */
    private void onClickDatePicker(final Calendar calendar,
                                   final DatePickerDialog.OnDateSetListener dateListener) {

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(context, dateListener,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }
}
