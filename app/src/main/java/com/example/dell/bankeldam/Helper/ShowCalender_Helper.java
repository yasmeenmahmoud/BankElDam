package com.example.dell.bankeldam.Helper;

import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class ShowCalender_Helper {
Context context;

    public ShowCalender_Helper(Context context) {
        this.context=context;
    }
    public void showCalender(final TextView text){
        final Calendar calendar = Calendar.getInstance();
        int yy = calendar.get(Calendar.YEAR);
        int mm = calendar.get(Calendar.MONTH);
        int dd = calendar.get(Calendar.DAY_OF_MONTH);
        final DatePickerDialog datePicker = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String date =
                        String.valueOf(year) + "-0" + String.valueOf(monthOfYear) + "-" + String.valueOf(dayOfMonth);
                text.setText(date);
            }
        }, yy, mm, dd);
        datePicker.show();



    }
}
