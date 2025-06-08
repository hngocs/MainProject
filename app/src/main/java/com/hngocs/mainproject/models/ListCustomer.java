package com.hngocs.mainproject.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class ListCustomer implements Serializable
{
    private ArrayList<Customer>customers;

    public ListCustomer() {
        customers=new ArrayList<>();
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(ArrayList<Customer> customers) {
        this.customers = customers;
    }
    public void addCustomer(Customer c)
    {
        customers.add(c);
    }
    public void generate_sample_dataset()
    {
        Random random=new Random();
        for(int i=1;i<=100;i++)
        {
            int id=i;
            String name="Customer "+i;
            String email="cutomer"+i+"@gmail.com";
            String username="customer"+i;
            String password="123";
            String phone="";
            int provider=random.nextInt(3);
            if(provider==0)
                phone="098";
            else if(provider==1)
                phone="090";
            else
                phone="094";
            for(int p=1;p<=7;p++)
            {
                phone+=random.nextInt(10);
            }
            Customer c=new Customer(id,name,email,phone,username,password);
            addCustomer(c);
        }
    }
    public boolean isExist(Customer c)
    {
        for(Customer cus : customers)
        {
            if(cus.getId()==c.getId() ||
                    cus.getPhone().equals(c.getPhone())||
                    cus.getEmail().equalsIgnoreCase(c.getEmail())||
                    cus.getUsername().equalsIgnoreCase(c.getUsername())
            )
                return true;
        }
        return false;
    }
}
