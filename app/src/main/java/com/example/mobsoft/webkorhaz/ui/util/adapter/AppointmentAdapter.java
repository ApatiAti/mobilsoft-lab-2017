package com.example.mobsoft.webkorhaz.ui.util.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mobsoft.webkorhaz.R;
import com.example.mobsoft.webkorhaz.model.Appointment;

import java.text.DateFormat;
import java.util.List;

/**
 * Created by Apati on 2017.04.23..
 */

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.AppointmentViewHolder> {

    List<Appointment> appointmentList;
    DateFormat dateFormat;

    public class AppointmentViewHolder extends RecyclerView.ViewHolder{
        TextView textViewDepartment;
        TextView textViewDate;
        TextView textViewRoom;

        public AppointmentViewHolder(View itemView) {
            super(itemView);
            textViewDepartment = (TextView) itemView.findViewById(R.id.textViewItemAppointmentDepartment);
            textViewDate = (TextView) itemView.findViewById(R.id.textViewItemAppointmentDate);
            textViewRoom = (TextView) itemView.findViewById(R.id.textViewItemAppointmentRoom);

        }
    }

    public AppointmentAdapter(List<Appointment> appointmentList, DateFormat dateFormat) {
        this.appointmentList = appointmentList;
        this.dateFormat = dateFormat;
    }


    // Create new views (invoked by the layout manager)
    @Override
    public AppointmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_appointment, parent, false);

        return new AppointmentViewHolder(itemView);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(AppointmentViewHolder holder, int position) {
        Appointment appointment = appointmentList.get(position);
        holder.textViewDepartment.setText(appointment.getDepartmentName());
        holder.textViewDate.setText(dateFormat.format(appointment.getBeginDate()));
        holder.textViewRoom.setText(appointment.getRoom());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return appointmentList.size();
    }

}
