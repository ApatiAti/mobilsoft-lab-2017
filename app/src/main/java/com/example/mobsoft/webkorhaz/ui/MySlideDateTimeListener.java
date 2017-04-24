package com.example.mobsoft.webkorhaz.ui;

import android.widget.TextView;
import android.widget.Toast;

import com.github.jjobes.slidedatetimepicker.SlideDateTimeListener;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Apati on 2017.04.24..
 */

public class MySlideDateTimeListener extends SlideDateTimeListener {

    private SimpleDateFormat mFormatter;
    private Date selectedDate = new Date();
    TextView textView;

    public MySlideDateTimeListener(TextView textView, SimpleDateFormat simpleDateFormat) {
        super();
        this.mFormatter = simpleDateFormat;
        this.textView = textView;
    }

    @Override
    public void onDateTimeSet(Date date) {
        setSelectedDate(date);

        String format = mFormatter.format(date);
        textView.setText(format);
    }

    public Date getSelectedDate() {
        return selectedDate;
    }

    public void setSelectedDate(Date selectedDate) {
        this.selectedDate = selectedDate;
    }
}
