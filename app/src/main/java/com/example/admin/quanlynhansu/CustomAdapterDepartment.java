package com.example.admin.quanlynhansu;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.admin.quanlynhansu.DTO.Department;

import java.util.List;

/**
 * Created by RPG_LOVER on 02/05/2017.
 */

public class CustomAdapterDepartment extends ArrayAdapter<Department>{
    List<Department> list;
    int resource;
    Context context;

    public CustomAdapterDepartment(Context context, int resource, List<Department> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.list = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.department_items, null);
        Department department = list.get(position);
        TextView txtID = (TextView)view.findViewById(R.id.txtIDDepartment);
        TextView txtName = (TextView)view.findViewById(R.id.txtDepartmentName);
        txtID.setText(String.valueOf(department.getID()));
        txtName.setText(department.getName());
        return view;
    }
}
