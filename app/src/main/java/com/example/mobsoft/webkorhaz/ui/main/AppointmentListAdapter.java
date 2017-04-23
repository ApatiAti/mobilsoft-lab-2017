package com.example.mobsoft.webkorhaz.ui.main;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mobsoft.webkorhaz.R;
import com.example.mobsoft.webkorhaz.model.Appointment;

import java.text.DateFormat;
import java.util.List;

/**
 * Created by Apati on 2017.04.23..
 */

public class AppointmentListAdapter extends ArrayAdapter<Appointment> {

    DateFormat dateFormat;

    public AppointmentListAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Appointment> objects) {
        super(context, resource, objects);
         dateFormat = android.text.format.DateFormat.getDateFormat(context.getApplicationContext());
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // TODO optimalize
        //  http://www.vogella.com/tutorials/AndroidListView/article.html#androidlists_overview
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.list_item_appointment, parent, false);

        TextView textViewDepartment = (TextView) rowView.findViewById(R.id.textViewItemAppointmentDepartment);
        TextView textViewDate = (TextView) rowView.findViewById(R.id.textViewItemAppointmentDate);
        TextView textViewRoom = (TextView) rowView.findViewById(R.id.textViewItemAppointmentRoom);

        Appointment item = getItem(position);

        textViewDepartment.setText(item.getDepartmentName());
        textViewDate.setText(dateFormat.format(item.getBeginDate()));
        textViewRoom.setText(item.getRoom());

        return rowView;
    }


}
