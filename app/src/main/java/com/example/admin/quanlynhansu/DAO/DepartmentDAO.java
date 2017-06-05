package com.example.admin.quanlynhansu.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.example.admin.quanlynhansu.DTO.Department;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 4/21/2017.
 */
public class DepartmentDAO {
    SQLiteDatabase db;
    MyDatabase myDatabase;
    Context context;

    public DepartmentDAO(Context context) {
        this.context = context;
        myDatabase = new MyDatabase(context);
    }

    public void AddDepartment (String name){
        db = myDatabase.getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues value = new ContentValues();
            value.put(myDatabase.getDepartmentName(), name);
            db.insert(myDatabase.getTableDepartment(), null, value);
            db.setTransactionSuccessful();
        }
        catch (SQLiteException ex){
            ex.printStackTrace();
        }
        finally {
            db.endTransaction();
        }
    }

    public void UpdateDepartment (Department dept){
        db = myDatabase.getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues value = new ContentValues();
            value.put(myDatabase.getDepartmentName(), dept.getName());
            db.update(myDatabase.getTableDepartment(), value, myDatabase.getDepartmentId() +
                    " = " + dept.getID(), null);
            db.setTransactionSuccessful();
        }
        catch (SQLiteException ex){
            ex.printStackTrace();
        }
        finally {
            db.endTransaction();
        }
    }

    public void DeleteDepartment (int id){
        db = myDatabase.getWritableDatabase();
        db.beginTransaction();
        try {
            db.delete(myDatabase.getTableDepartment(), myDatabase.getDepartmentId() +
                    " = " + id, null);
            db.setTransactionSuccessful();
        }
        catch (SQLiteException ex){
            ex.printStackTrace();
        }
        finally {
            db.endTransaction();
        }
    }

    public List<Department> getDepartment() {
        List<Department> list = new ArrayList<Department>();
        db = myDatabase.getReadableDatabase();
        try {
            String sql = "select * from " + myDatabase.getTableDepartment();
            Cursor cursor = db.rawQuery(sql, null);
            cursor.moveToPosition(-1);
            while (cursor.moveToNext()) {
                Department department = new Department(
                        cursor.getInt(0),
                        cursor.getString(1)
                );
                list.add(department);
            }
        } catch (SQLiteException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public List<String> getDepartmentName() {
        List<String> list = new ArrayList<String>();
        db = myDatabase.getReadableDatabase();
        try {
            String sql = "select * from " + myDatabase.getTableDepartment();
            Cursor cursor = db.rawQuery(sql, null);
            cursor.moveToPosition(-1);
            while (cursor.moveToNext()) {
                list.add(cursor.getString(cursor.getColumnIndex(
                        myDatabase.getDepartmentName())));
            }
        } catch (SQLiteException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public List<Integer> getDepartmentID() {
        List<Integer> list = new ArrayList<Integer>();
        db = myDatabase.getReadableDatabase();
        try {
            String sql = "select * from " + myDatabase.getTableDepartment();
            Cursor cursor = db.rawQuery(sql, null);
            cursor.moveToPosition(-1);
            while (cursor.moveToNext()) {
                list.add(cursor.getInt(cursor.getColumnIndex(
                        myDatabase.getDepartmentId())));
            }
        } catch (SQLiteException ex) {
            ex.printStackTrace();
        }
        return list;
    }
}
