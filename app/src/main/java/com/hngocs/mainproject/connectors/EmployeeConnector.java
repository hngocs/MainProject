package com.hngocs.mainproject.connectors;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hngocs.mainproject.models.Employee;
import com.hngocs.mainproject.models.ListEmployee;


public class EmployeeConnector {
    public Employee login(SQLiteDatabase database, String usr, String pwd)
    {
        Cursor cursor = database.rawQuery(
                "SELECT * FROM Employee WHERE UserName = ? AND Password= ?",
                new String[]{usr,pwd});
        Employee emp=null;
        while(cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String email=cursor.getString(2);
            String phone=cursor.getString(3);
            String username=cursor.getString(4);
            String password=cursor.getString(5);
            emp=new Employee();
            emp.setId(id);
            emp.setName(name);
            emp.setEmail(email);
            emp.setPhone(phone);
            emp.setUsername(username);
            emp.setPassword(password);
        }
        cursor.close();

        return emp;
    }
    public Employee login(String usr,String pwd)
    {
        ListEmployee le=new ListEmployee();
        le.gen_dataset();
        for (Employee emp : le.getEmployees())
        {
            if (emp.getUsername().equalsIgnoreCase(usr) && emp.getPassword().equals(pwd))
            {
                return emp;
            }
        }
        return null;
    }
}
