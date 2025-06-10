package com.hngocs.mainproject.screens;

//import android.os.Bundle;
//import android.widget.ListView;
//
//import androidx.activity.EdgeToEdge;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.graphics.Insets;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.WindowInsetsCompat;
//
//import com.hngocs.mainproject.R;
//import com.hngocs.mainproject.adapters.PaymentMethodAdapter;
//import com.hngocs.mainproject.models.ListPaymentMethod;
//
//public class PaymentMethodActivity extends AppCompatActivity {
//
//    ListView lvPaymentMethod;
//    PaymentMethodAdapter adapter;
//    ListPaymentMethod lpm;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_payment_method);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
//        addViews();
//    }
//
//    private void addViews() {
//        lvPaymentMethod=findViewById(R.id.lvPaymentMethod);
//        adapter=new PaymentMethodAdapter(PaymentMethodActivity.this,R.layout.item_paymentmethod);
//        lvPaymentMethod.setAdapter(adapter);
//        lpm=new ListPaymentMethod();
//        lpm.gen_payments_method();
//        adapter.addAll(lpm.getPaymentMethods());
//    }
//}
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase; // Import này cần thiết
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter; // Giữ lại ArrayAdapter
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.hngocs.mainproject.R;
import com.hngocs.mainproject.connectors.PaymentMethodConnector; // Import connector mới
import com.hngocs.mainproject.connectors.SQLiteConnector; // Import SQLiteConnector
import com.hngocs.mainproject.models.PaymentMethod; // Import model

import java.util.List; // Import List

public class PaymentMethodActivity extends AppCompatActivity {

    ListView lvPaymentMethod;
    ArrayAdapter<PaymentMethod> adapter; // Vẫn dùng ArrayAdapter
    PaymentMethodConnector connector; // Thêm connector
    SQLiteConnector sqLiteConnector; // Thêm SQLiteConnector để quản lý database

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_payment_method); // Layout này đã có
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> { // ID này phải có trong layout của bạn
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addViews();
        addEvents(); // Thêm lại addEvents nếu bạn muốn xử lý click
    }

    private void addEvents() {
        lvPaymentMethod.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                PaymentMethod pm = adapter.getItem(i);
                if (pm != null) {
                    Toast.makeText(PaymentMethodActivity.this,
                            "Bạn đã nhấn giữ: " + pm.getName() + " - " + pm.getDescription(),
                            Toast.LENGTH_LONG).show();
                    // Tùy chọn: Mở màn hình chi tiết/sửa PaymentMethod
                    // Intent intent = new Intent(PaymentMethodActivity.this, PaymentMethodDetailActivity.class);
                    // intent.putExtra("SELECTED_PAYMENT_METHOD", pm);
                    // startActivity(intent);
                }
                return true; // Trả về true để báo hiệu đã xử lý sự kiện
            }
        });
        // Tùy chọn: Xử lý click ngắn
        lvPaymentMethod.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                PaymentMethod pm = adapter.getItem(i);
                if (pm != null) {
                    Toast.makeText(PaymentMethodActivity.this,
                            "Bạn đã chọn: " + pm.getName(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void addViews() {
        lvPaymentMethod = findViewById(R.id.lvPaymentMethod);
        // Không cần truyền R.layout.item_paymentmethod vào ArrayAdapter nếu chỉ hiển thị 1 dòng text
        // Nếu bạn muốn hiển thị nhiều thông tin hơn (ví dụ: tên và mô tả trên 2 dòng),
        // bạn sẽ cần một PaymentMethodAdapter TÙY CHỈNH và layout item_paymentmethod.xml
        adapter = new ArrayAdapter<>(
                PaymentMethodActivity.this,
                android.R.layout.simple_list_item_1 // Sử dụng layout có sẵn của Android cho 1 dòng text
        );
        lvPaymentMethod.setAdapter(adapter);

        sqLiteConnector = new SQLiteConnector(this); // Khởi tạo SQLiteConnector
        connector = new PaymentMethodConnector(); // Khởi tạo PaymentMethodConnector

        // Gọi phương thức tải dữ liệu từ database
        loadPaymentMethodsFromDatabase();
    }

    // Phương thức mới để tải dữ liệu từ database
    private void loadPaymentMethodsFromDatabase() {
        SQLiteDatabase database = null;
        try {
            database = sqLiteConnector.openDatabase(); // Mở database
            List<PaymentMethod> paymentMethods = connector.getAllPaymentMethods(database); // Lấy dữ liệu
            adapter.clear(); // Xóa dữ liệu cũ trong adapter
            adapter.addAll(paymentMethods); // Thêm dữ liệu mới vào adapter
            adapter.notifyDataSetChanged(); // Thông báo cho adapter dữ liệu đã thay đổi
        } catch (Exception e) {
            Toast.makeText(this, "Lỗi khi tải dữ liệu phương thức thanh toán: " + e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        } finally {
            if (database != null) {
                sqLiteConnector.closeDatabase(); // Đảm bảo đóng database sau khi sử dụng
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Tải lại dữ liệu mỗi khi Activity trở lại trạng thái resumed
        // Điều này hữu ích nếu bạn có màn hình thêm/sửa và muốn cập nhật danh sách
        loadPaymentMethodsFromDatabase();
    }


    // --- Xử lý kết quả trả về từ Activity khác (nếu bạn có màn hình chi tiết/thêm mới) ---
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Ví dụ: Nếu màn hình thêm mới trả về OK (resultCode = RESULT_OK)
        // và requestCode khớp với code bạn dùng khi gọi startActivityForResult
        if (requestCode == 400 && resultCode == RESULT_OK) { // Giả sử 400 là request code thêm mới
            Toast.makeText(this, "Đã thêm/sửa phương thức thanh toán thành công!", Toast.LENGTH_SHORT).show();
            loadPaymentMethodsFromDatabase(); // Tải lại danh sách để cập nhật giao diện
        }
    }
}