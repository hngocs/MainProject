package com.hngocs.mainproject.adapters;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.hngocs.mainproject.R;
import com.hngocs.mainproject.models.TelephonyInfor;
import com.hngocs.mainproject.screens.SendSMSActivity;
import com.hngocs.mainproject.screens.TelephonyActivity;

public class TelephonyInforAdapter extends ArrayAdapter<TelephonyInfor>
{
    Activity context;
    int resource;
    public TelephonyInforAdapter(@NonNull Activity context, int resource) {
        super(context, resource);
        this.context=context;
        this.resource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=this.context.getLayoutInflater();
        View item=inflater.inflate(this.resource,null);
        TextView txtTelephonyName=item.findViewById(R.id.txtTelephonyName);
        TextView txtTelephonyNumber=item.findViewById(R.id.txtTelephonyNumber);
        ImageView imgDirectCall=item.findViewById(R.id.imgDirectCall);
        ImageView imgDialUp=item.findViewById(R.id.imgDialUp);
        ImageView imgSms=item.findViewById(R.id.imgSendSms);

        TelephonyInfor ti=getItem(position);
        txtTelephonyName.setText(ti.getName());
        txtTelephonyNumber.setText(ti.getPhone());
        //các sự kiện making telephony làm sau
        imgDirectCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((TelephonyActivity)context).directCall(ti);
            }
        });

        imgDialUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((TelephonyActivity)context).dialupCall(ti);
            }
        });

        imgSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, SendSMSActivity.class);
                intent.putExtra("TI",ti);
                context.startActivity(intent);
            }
        });

        return item;
    }
}

