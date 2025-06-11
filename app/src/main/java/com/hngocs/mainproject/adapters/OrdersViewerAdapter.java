package com.hngocs.mainproject.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.hngocs.mainproject.R;
import com.hngocs.mainproject.models.OrdersViewer;

public class OrdersViewerAdapter extends ArrayAdapter<OrdersViewer>
{
    Activity context;
    int resource;
    public OrdersViewerAdapter(@NonNull Activity context, int resource) {
        super(context, resource);
        this.context=context;
        this.resource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=this.context.getLayoutInflater();
        View item=inflater.inflate(this.resource,null);

        TextView txtOrderCode=item.findViewById(R.id.txtOrderCode);
        TextView txtOrderDate=item.findViewById(R.id.txtOrderDate);
        TextView txtCustomerName=item.findViewById(R.id.txtCustomerName);
        TextView txtEmployeeName=item.findViewById(R.id.txtEmployeeName);
        TextView txtTotalOrderValue=item.findViewById(R.id.txtTotalOrderValue);

        OrdersViewer ov=getItem(position);
        txtOrderCode.setText(ov.getCode());
        txtOrderDate.setText(ov.getOrderDate());
        txtCustomerName.setText(ov.getCustomerName());
        txtEmployeeName.setText(ov.getEmployeeName());
        txtTotalOrderValue.setText(ov.getTotalOrderValue() +"VNĐ");

        return item;
    }
}
