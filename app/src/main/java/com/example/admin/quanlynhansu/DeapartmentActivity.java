package com.example.admin.quanlynhansu;

import android.app.Dialog;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.quanlynhansu.DAO.DepartmentDAO;
import com.example.admin.quanlynhansu.DTO.Department;

import java.util.List;

public class DeapartmentActivity extends AppCompatActivity {
    List<Department> list;
    CustomAdapterDepartment adapterDepartment;
    DepartmentDAO deptDAO;
    ListView ListDepartment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deapartment);
        deptDAO = new DepartmentDAO(getApplicationContext());
        ListDepartment = (ListView)findViewById(R.id.listview_department);
        LoadDepartment();

        ListDepartment.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                PopupMenu menu = new PopupMenu(DeapartmentActivity.this, view);
                menu.getMenuInflater().inflate(R.menu.popup_menu, menu.getMenu());
                menu.show();
                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int id = item.getItemId();
                        final Department department = list.get(i);
                        if(id == R.id.Update){
                            final Dialog dialog = new Dialog(DeapartmentActivity.this);
                            dialog.setContentView(R.layout.action_department);
                            dialog.setTitle("Cập nhật");

                            TextView txtID = (TextView)dialog.findViewById(R.id.txtID);
                            final EditText edtName = (EditText)dialog.findViewById(R.id.edtName);
                            Button btnUpdate = (Button)dialog.findViewById(R.id.btnUpdate);
                            dialog.getWindow().setLayout(WindowManager.LayoutParams.FILL_PARENT,
                                    WindowManager.LayoutParams.WRAP_CONTENT);
                            dialog.show();
                            txtID.setText("ID: " + String.valueOf(department.getID()));
                            edtName.setText(department.getName());
                            btnUpdate.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    try{
                                        Department dept = new Department(department.getID(),
                                                edtName.getText().toString());
                                        deptDAO.UpdateDepartment(dept);
                                        dialog.dismiss();
                                        LoadDepartment();
                                        Toast.makeText(DeapartmentActivity.this, "Hoàn tất.",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                    catch (Exception ex){
                                        Toast.makeText(DeapartmentActivity.this, "Lỗi: " + ex.getMessage().toString(),
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }

                        if (id == R.id.Delete){
                            try{
                                deptDAO.DeleteDepartment(department.getID());
                                LoadDepartment();
                                Toast.makeText(DeapartmentActivity.this, "Hoàn tất.",
                                        Toast.LENGTH_SHORT).show();
                            }
                            catch (Exception ex){
                                Toast.makeText(DeapartmentActivity.this, "Lỗi: " + ex.getMessage().toString(),
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                        return true;
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

    public void LoadDepartment(){
        list = deptDAO.getDepartment();
        adapterDepartment = new CustomAdapterDepartment(DeapartmentActivity.this, R.layout.department_items,
                list);
        ListDepartment.setAdapter(adapterDepartment);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.btnAdd) {
            final Dialog dialog = new Dialog(this);
            dialog.setTitle("Thêm phòng ban");
            dialog.setContentView(R.layout.add_department);

            final EditText edtDeptName = (EditText) dialog.findViewById(R.id.edtIDDPM);
            Button btnAdd = (Button) dialog.findViewById(R.id.btnAdd);
            dialog.getWindow().setLayout(WindowManager.LayoutParams.FILL_PARENT,
                    WindowManager.LayoutParams.WRAP_CONTENT);
            dialog.show();

            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deptDAO.AddDepartment(edtDeptName.getText().toString());
                    LoadDepartment();
                    dialog.dismiss();
                }
            });
        }
        return false;
    }
}
