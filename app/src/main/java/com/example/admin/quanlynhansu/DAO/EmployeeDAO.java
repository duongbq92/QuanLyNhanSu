package com.example.admin.quanlynhansu.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.example.admin.quanlynhansu.DTO.Employee;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RPG_LOVER on 04/05/2017.
 */

public class EmployeeDAO {
    SQLiteDatabase db;
    MyDatabase myDatabase;
    Context context;

    public EmployeeDAO(Context context) {
        this.context = context;
        myDatabase = new MyDatabase(context);
    }

    public void AddEmployee (Employee employee){
        db = myDatabase.getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(myDatabase.getEmployeeFirstname(), employee.getFirstName());
            values.put(myDatabase.getEmployeeLastname(), employee.getLastName());
            values.put(myDatabase.getEmployeeDepartmentIdFk(), employee.getDepartment_id());
            db.insert(myDatabase.getTableEmployee(), null, values);
            db.setTransactionSuccessful();
        }
        catch (SQLiteException ex){
            ex.printStackTrace();
        }
        finally {
            db.endTransaction();
        }
    }

    public void UpdateEmployee (Employee employee){
        db = myDatabase.getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(myDatabase.getEmployeeFirstname(), employee.getFirstName());
            values.put(myDatabase.getEmployeeLastname(), employee.getLastName());
            values.put(myDatabase.getEmployeeDepartmentIdFk(), employee.getDepartment_id());
            db.update(myDatabase.getTableEmployee(), values, myDatabase.getEmployeeId() + " = " +
                    employee.getEmployee_id(), null);
            db.setTransactionSuccessful();
        }
        catch (SQLiteException ex){
            ex.printStackTrace();
        }
        finally {
            db.endTransaction();
        }
    }

    public void DeleteEmployee (int id){
        db = myDatabase.getWritableDatabase();
        db.beginTransaction();
        try {
            db.delete(myDatabase.getTableEmployee(), myDatabase.getEmployeeId() + " = " + id,
                    null);
            db.setTransactionSuccessful();
        }
        catch (SQLiteException ex){
            ex.printStackTrace();
        }
        finally {
            db.endTransaction();
        }
    }
    public List<Employee> getEmployee(){
        List<Employee> list = new ArrayList<Employee>();
        db = myDatabase.getReadableDatabase();
        try {
            String sql = "select * from " + myDatabase.getTableEmployee() + ", " +
                    myDatabase.getTableDepartment() + " where " + myDatabase.getTableEmployee() + "."
                    + myDatabase.getEmployeeDepartmentIdFk() + " = " + myDatabase.getTableDepartment()
                    + "." + myDatabase.getDepartmentId();
            Cursor cursor = db.rawQuery(sql, null);
            cursor.moveToPosition(-1);
            while (cursor.moveToNext()){
                Employee employee = new Employee(cursor.getInt(0),
                        cursor.getString(cursor.getColumnIndex(myDatabase.getEmployeeFirstname())),
                        cursor.getString(cursor.getColumnIndex(myDatabase.getEmployeeLastname())),
                        cursor.getInt(cursor.getColumnIndex(myDatabase.getDepartmentId())),
                        cursor.getString(cursor.getColumnIndex(myDatabase.getDepartmentName())));
                list.add(employee);
            }
        }
        catch (SQLiteException ex){
            ex.printStackTrace();
        }
        return list;
    }
}
