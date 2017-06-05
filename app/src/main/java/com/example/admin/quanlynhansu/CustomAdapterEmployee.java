package com.example.admin.quanlynhansu;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.admin.quanlynhansu.DTO.Employee;

import java.util.List;

/**
 * Created by RPG_LOVER on 02/05/2017.
 */

public class CustomAdapterEmployee extends ArrayAdapter<Employee>{
    List<Employee> list;
    int resource;
    Context context;

    public CustomAdapterEmployee(Context context, int resource, List<Employee> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.list = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.employee_info, null);

        Employee employee = list.get(position);
        TextView txtID = (TextView)view.findViewById(R.id.txtID);
        TextView txtFullName = (TextView)view.findViewById(R.id.txtFullName);
        TextView txtDeptID = (TextView)view.findViewById(R.id.txtDepartmentName);

        txtID.setText("ID: " + String.valueOf(employee.getEmployee_id()));
        txtFullName.setText("Họ tên: " + employee.getLastName() + " " + employee.getFirstName());
        txtDeptID.setText("Bộ phận: " + employee.getDepartment_id() + ". "
                + employee.getDepartment_name());

        return view;
    }
}
