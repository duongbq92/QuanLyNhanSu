package com.example.admin.quanlynhansu;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.admin.quanlynhansu.DAO.DepartmentDAO;
import com.example.admin.quanlynhansu.DAO.EmployeeDAO;
import com.example.admin.quanlynhansu.DTO.Department;
import com.example.admin.quanlynhansu.DTO.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class StaffActivity extends AppCompatActivity {
    List<Employee> list;
    List<Department> departmentList;
    CustomAdapterEmployee adapterEmployee;
    EmployeeDAO employeeDAO;
    DepartmentDAO deptDAO;
    GridView gridViewEmployee;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff);
        employeeDAO = new EmployeeDAO(getApplicationContext());
        deptDAO = new DepartmentDAO(getApplicationContext());
        gridViewEmployee = (GridView)findViewById(R.id.gridView_employee);
        LoadEmployeeList();

        gridViewEmployee.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                final Dialog dialog = new Dialog(StaffActivity.this);
                dialog.setTitle("Thông tin nhân viên");
                dialog.setContentView(R.layout.action_employee);

                final EditText edtFirstName = (EditText)dialog.findViewById(R.id.edtDLFirstName);
                final EditText edtLastName = (EditText)dialog.findViewById(R.id.edtDLLastName);
                final EditText edtDepartmentID = (EditText)dialog.findViewById(R.id.edtDepartmentID);
                Button btnUpdate = (Button)dialog.findViewById(R.id.btnUpdateEmployee);
                Button btnDelete = (Button)dialog.findViewById(R.id.btnDelEmployee);
                dialog.show();
                dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                        WindowManager.LayoutParams.WRAP_CONTENT);

                Employee employee = list.get(i);
                edtFirstName.setText(employee.getFirstName());
                edtLastName.setText(employee.getLastName());
                edtDepartmentID.setText(String.valueOf(employee.getDepartment_id()));
                final int id = employee.getEmployee_id();

                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try{
                            Employee employee = new Employee(id,
                                    edtFirstName.getText().toString(),
                                    edtLastName.getText().toString(),
                                    Integer.parseInt(edtDepartmentID.getText().toString())
                            );
                            employeeDAO.UpdateEmployee(employee);
                            dialog.dismiss();
                            Toast.makeText(StaffActivity.this, "Thành công.", Toast.LENGTH_SHORT).show();
                            LoadEmployeeList();
                        }
                        catch (Exception ex){
                            Toast.makeText(StaffActivity.this, "Lỗi: " + ex.getMessage().toString(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try{
                            employeeDAO.DeleteEmployee(id);
                            dialog.dismiss();
                            Toast.makeText(StaffActivity.this, "Thành công.", Toast.LENGTH_SHORT).show();
                            LoadEmployeeList();
                        }
                        catch (Exception ex){
                            Toast.makeText(StaffActivity.this, "Lỗi: " + ex.getMessage().toString(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_department, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.btnAdd){
            final Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.add_employee);
            dialog.setTitle("Thêm nhân viên");

            final EditText edtFirstName = (EditText)dialog.findViewById(R.id.edtFirstName);
            final EditText edtLastName = (EditText)dialog.findViewById(R.id.edtLastName);
            final Spinner spinner = (Spinner)dialog.findViewById(R.id.spinnerDepartment);
            Button btnAdd = (Button)dialog.findViewById(R.id.btnAddEmployee);
            dialog.show();
            dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.WRAP_CONTENT);
            List<Integer> spinnerList = deptDAO.getDepartmentID();
            ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(StaffActivity.this,
                    android.R.layout.simple_spinner_dropdown_item, spinnerList);
            spinner.setAdapter(adapter);

            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try{
                        Employee employee = new Employee(edtFirstName.getText().toString(),
                                edtLastName.getText().toString(),
                                Integer.parseInt(spinner.getSelectedItem().toString()));
                        employeeDAO.AddEmployee(employee);
                        dialog.dismiss();
                        Toast.makeText(StaffActivity.this, "Thành công.", Toast.LENGTH_SHORT).show();
                        LoadEmployeeList();
                    }
                    catch (Exception ex){
                        Toast.makeText(StaffActivity.this, "Lỗi: " + ex.getMessage().toString(),
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        return false;
    }

    public void LoadEmployeeList(){
        list = employeeDAO.getEmployee();
        adapterEmployee = new CustomAdapterEmployee(StaffActivity.this, R.layout.employee_info, list);
        gridViewEmployee.setAdapter(adapterEmployee);
    }
}
