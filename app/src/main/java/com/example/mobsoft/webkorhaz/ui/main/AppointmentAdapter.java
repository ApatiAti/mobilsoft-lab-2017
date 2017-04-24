package com.example.mobsoft.webkorhaz.ui.main;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mobsoft.webkorhaz.R;
import com.example.mobsoft.webkorhaz.model.Appointment;

import java.text.DateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.security.AccessController.getContext;

/**
 * Created by Apati on 2017.04.23..
 */

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.MyViewHolder> {

    List<Appointment> appointmentList;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textViewDepartment;
        TextView textViewDate;
        TextView textViewRoom;

        public MyViewHolder(View itemView) {
            super(itemView);
            textViewDepartment = (TextView) itemView.findViewById(R.id.textViewItemAppointmentDepartment);
            textViewDate = (TextView) itemView.findViewById(R.id.textViewItemAppointmentDate);
            textViewRoom = (TextView) itemView.findViewById(R.id.textViewItemAppointmentRoom);

        }
    }

    public AppointmentAdapter(List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }


    // Create new views (invoked by the layout manager)
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_appointment, parent, false);

        return new MyViewHolder(itemView);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Appointment appointment = appointmentList.get(position);
        holder.textViewDepartment.setText(appointment.getDepartmentName());
        String date = DateUtils.formatDateTime(holder.textViewDate.getContext(), appointment.getBeginDate().getTime(), DateUtils.FORMAT_ABBREV_ALL);
        holder.textViewDate.setText(date);
        holder.textViewRoom.setText(appointment.getRoom());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return appointmentList.size();
    }

}
