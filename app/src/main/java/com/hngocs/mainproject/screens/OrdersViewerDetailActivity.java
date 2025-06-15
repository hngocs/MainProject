package com.hngocs.mainproject.screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.hngocs.mainproject.R;
import com.hngocs.mainproject.models.OrdersViewer;

public class OrdersViewerDetailActivity extends AppCompatActivity {

    EditText edt_ordersviewer_id;
    EditText edt_ordersviewer_code;
    EditText edt_ordersviewer_employee_name;
    EditText edt_ordersviewer_customer_name;
    EditText edt_ordersviewer_total_value;

    Button btnNew;
    Button btnSave;
    Button btnRemove;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_orders_viewer_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addViews();
        addEvents();
    }

    private void addEvents() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                process_save_order();
            }
        });
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                process_remove_order();
            }
        });
    }

    private void process_remove_order() {
        Intent intent=getIntent();
        String id=edt_ordersviewer_id.getText().toString();
        intent.putExtra("ORDER_ID_REMOVE",id);
        setResult(800,intent);
        finish();

    }

    private void process_save_order() {

        OrdersViewer o=new OrdersViewer();
        String id=edt_ordersviewer_id.getText().toString();
        if(id.trim().length()>0)
            o.setId(Integer.parseInt(id));
        o.setCode(edt_ordersviewer_code.getText().toString());
        o.setEmployeeName(edt_ordersviewer_employee_name.getText().toString());
        o.setCustomerName(edt_ordersviewer_customer_name.getText().toString());
//        o.setTotalOrderValue(edt_ordersviewer_total_value).getText().toString();
        double total = Double.parseDouble(edt_ordersviewer_total_value.getText().toString());
        o.setTotalOrderValue(total);

        //lấy Intent từ màn hình gọi nó:
        Intent intent=getIntent();
        //đóng gói dữ liệu vào intent:
//        intent.putExtra("NEW_ORDER",o);
        //đóng dấu là sẽ gửi nói hàng này đi:
        setResult(700,intent);
        //đóng màn hình này lại, để màn hình gọi nó nhận được kết quả:
        finish();

    }

    private void addViews() {
        edt_ordersviewer_id=findViewById(R.id.edt_customer_id);
        edt_ordersviewer_code=findViewById(R.id.edt_customer_name);
        edt_ordersviewer_employee_name=findViewById(R.id.edt_customer_email);
        edt_ordersviewer_customer_name=findViewById(R.id.edt_customer_phone);
        edt_ordersviewer_total_value=findViewById(R.id.edt_customer_username);
        display_infor();

        btnNew=findViewById(R.id.btnNew);
        btnSave=findViewById(R.id.btnSave);
        btnRemove=findViewById(R.id.btnRemove);
    }

    private void display_infor() {
        //Lấy Intent từ bên CustomerManagementActivity gửi qua:
        Intent intent=getIntent();
        //Lấy dữ liệu Selected Customer từ intent:
        OrdersViewer o= (OrdersViewer) intent.getSerializableExtra("SELECTED_ORDER");
        if(o==null)
        {
            edt_ordersviewer_id.setVisibility(View.GONE);
            return;
        }
        //Hiển thị thông tin Customer lên giao diện:
        edt_ordersviewer_id.setText(o.getId()+"");
        edt_ordersviewer_code.setText(o.getCode());
        edt_ordersviewer_employee_name.setText(o.getEmployeeName());
        edt_ordersviewer_customer_name.setText(o.getCustomerName());
        edt_ordersviewer_total_value.setText(String.valueOf(o.getTotalOrderValue()));
    }
}






