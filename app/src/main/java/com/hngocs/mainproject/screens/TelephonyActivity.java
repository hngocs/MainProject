package com.hngocs.mainproject.screens;

//import android.content.Intent;
//import android.database.Cursor;
//import android.net.Uri;
//import android.os.Bundle;
//import android.provider.ContactsContract;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.ListView;
//
//import androidx.activity.EdgeToEdge;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.graphics.Insets;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.WindowInsetsCompat;
//
//import com.hngocs.mainproject.R;
//import com.hngocs.mainproject.adapters.TelephonyInforAdapter;
//import com.hngocs.mainproject.models.TelephonyInfor;
//
//public class TelephonyActivity extends AppCompatActivity {
//
//    ListView lvTelephony;
//    //ArrayAdapter<TelephonyInfor> adapter;
//    TelephonyInforAdapter adapter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_telephony);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
//        addViews();
//        getAllContacts();
//
//        addEvents();
//    }
//
//    private void addEvents() {
//        lvTelephony.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                TelephonyInfor ti=adapter.getItem(i);
//                makeAPhoneCall(ti);
//            }
//        });
//    }
//
//    private void makeAPhoneCall(TelephonyInfor ti) {
//        Uri uri=Uri.parse("tel:"+ti.getPhone());
//        Intent intent=new Intent(Intent.ACTION_CALL);
//        intent.setData(uri);
//        startActivity(intent);
//    }
//
//    private void getAllContacts() {
//        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
//        Cursor cursor = getContentResolver().query(uri, null,  null, null,  null);
//
//        adapter.clear();
//
//        while (cursor.moveToNext()){
//            int nameIndex =cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
//            String name = cursor.getString(nameIndex); //Get Name
//            int phoneIndex =cursor.getColumnIndex(ContactsContract.CommonDataKinds. Phone.NUMBER);
//            String phone = cursor.getString(phoneIndex); //Get Phone Number
//
//            TelephonyInfor ti=new TelephonyInfor();
//            ti.setName(name);
//            ti.setPhone(phone);
//            adapter.add(ti);
//        }
//        cursor.close();
//
//
//    }
//
//    private void addViews() {
//        lvTelephony=findViewById(R.id.lvTelephonyInfor);
//        //adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
//        adapter=new TelephonyInforAdapter(this,R.layout.item_telephony_infor);
//        lvTelephony.setAdapter(adapter);
//    }
//
//    public void directCall(TelephonyInfor ti)
//    {
//        Uri uri=Uri.parse("tel:"+ti.getPhone());
//        Intent intent=new Intent(Intent.ACTION_CALL);
//        intent.setData(uri);
//        startActivity(intent);
//    }
//    public void dialupCall(TelephonyInfor ti)
//    {
//        Uri uri=Uri.parse("tel:"+ti.getPhone());
//        Intent intent=new Intent(Intent.ACTION_DIAL);
//        intent.setData(uri);
//        startActivity(intent);
//    }
//}

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.hngocs.mainproject.R;
import com.hngocs.mainproject.adapters.TelephonyInforAdapter;
import com.hngocs.mainproject.models.TelephonyInfor;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class TelephonyActivity extends AppCompatActivity {

    ListView lvTelephony;
    TelephonyInforAdapter adapter;
    List<TelephonyInfor> allContacts; // Để lưu trữ tất cả liên hệ gốc

    // Hằng số cho Request Code của quyền
    private static final int PERMISSION_REQUEST_CODE_READ_CONTACTS = 1;
    private static final int PERMISSION_REQUEST_CODE_CALL_PHONE = 2;

    // Biến tạm để lưu trữ TelephonyInfor khi yêu cầu quyền gọi điện
    private TelephonyInfor tempTelephonyInforForCall;

    // Regular expressions cho các nhà mạng
    // Lưu ý: Các đầu số có thể thay đổi theo thời gian, cần cập nhật nếu có
    private static final String VIETTEL_REGEX = "^(0|\\+84)(32|33|34|35|36|37|38|39|86|96|97|98)\\d{7}$";
    private static final String MOBIFONE_REGEX = "^(0|\\+84)(70|76|77|78|79|89|90|93)\\d{7}$";
    private static final String VINAPHONE_REGEX = "^(0|\\+84)(81|82|83|84|85|88|91|94)\\d{7}$";
    private static final String VIETNAMOBILE_REGEX = "^(0|\\+84)(52|56|58|92)\\d{7}$";
    private static final String GMOBILE_REGEX = "^(0|\\+84)(59|99)\\d{7}$";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_telephony);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addViews();
        allContacts = new ArrayList<>(); // Khởi tạo danh sách lưu trữ tất cả liên hệ
        checkAndRequestContactPermission(); // Tải tất cả liên hệ vào allContacts và hiển thị sau khi có quyền
        addEvents();
    }

    private void addEvents() {
        lvTelephony.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TelephonyInfor ti = adapter.getItem(i);
                if (ti != null) {
                    tempTelephonyInforForCall = ti; // Lưu tạm thông tin để gọi sau khi cấp quyền
                    checkAndRequestCallPermission(ti);
                }
            }
        });
    }

    private void makeAPhoneCall(TelephonyInfor ti) {
        Uri uri = Uri.parse("tel:" + ti.getPhone());
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(uri);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(this, "Không tìm thấy ứng dụng điện thoại để thực hiện cuộc gọi", Toast.LENGTH_SHORT).show();
        }
    }

    // Phương thức kiểm tra và yêu cầu quyền đọc danh bạ
    private void checkAndRequestContactPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, PERMISSION_REQUEST_CODE_READ_CONTACTS);
        } else {
            getAllContacts(); // Quyền đã được cấp, tải danh bạ
        }
    }

    // Phương thức kiểm tra và yêu cầu quyền gọi điện
    private void checkAndRequestCallPermission(TelephonyInfor ti) {
        tempTelephonyInforForCall = ti; // Luôn lưu tạm thông tin liên hệ khi yêu cầu quyền gọi
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, PERMISSION_REQUEST_CODE_CALL_PHONE);
        } else {
            makeAPhoneCall(ti); // Quyền đã được cấp, thực hiện cuộc gọi
        }
    }

    // Xử lý kết quả yêu cầu quyền
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE_READ_CONTACTS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getAllContacts(); // Quyền đọc danh bạ được cấp, tải danh bạ
            } else {
                Toast.makeText(this, "Quyền đọc danh bạ bị từ chối. Không thể hiển thị danh bạ.", Toast.LENGTH_LONG).show();
            }
        } else if (requestCode == PERMISSION_REQUEST_CODE_CALL_PHONE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (tempTelephonyInforForCall != null) {
                    makeAPhoneCall(tempTelephonyInforForCall); // Quyền gọi điện được cấp, thực hiện cuộc gọi đã lưu
                    tempTelephonyInforForCall = null; // Xóa thông tin tạm
                }
            } else {
                Toast.makeText(this, "Quyền gọi điện bị từ chối. Không thể thực hiện cuộc gọi trực tiếp.", Toast.LENGTH_LONG).show();
            }
        }
    }

    // Phương thức tải tất cả liên hệ từ thiết bị
    private void getAllContacts() {
        allContacts.clear(); // Xóa dữ liệu cũ trước khi tải lại
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                int nameIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
                String name = (nameIndex != -1) ? cursor.getString(nameIndex) : "Unknown Name";

                int phoneIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                String phone = (phoneIndex != -1) ? cursor.getString(phoneIndex) : ""; // Để rỗng nếu không có số

                if (!phone.isEmpty()) { // Chỉ thêm các số điện thoại không rỗng
                    TelephonyInfor ti = new TelephonyInfor();
                    ti.setName(name);
                    ti.setPhone(normalizePhoneNumber(phone)); // Chuẩn hóa số điện thoại
                    allContacts.add(ti);
                }
            }
            cursor.close();
        }
        // Sau khi tải tất cả, hiển thị lên ListView
        displayContacts(allContacts);
    }

    // Phương thức chuẩn hóa số điện thoại (loại bỏ khoảng trắng, dấu gạch ngang, thêm +84 nếu cần)
    private String normalizePhoneNumber(String phone) {
        String normalizedPhone = phone.replaceAll("[\\s-.]", ""); // Loại bỏ khoảng trắng, dấu gạch ngang, dấu chấm
        normalizedPhone = normalizedPhone.replaceFirst("^\\+84", ""); // Loại bỏ +84 nếu có để chuẩn hóa sang 0 đầu tiên
        normalizedPhone = normalizedPhone.replaceFirst("^84", ""); // Loại bỏ 84 nếu có

        if (normalizedPhone.startsWith("0")) {
            return normalizedPhone; // Giữ nguyên 0 nếu đã có
        } else {
            return "0" + normalizedPhone; // Thêm 0 vào đầu nếu không có
        }
    }


    private void addViews() {
        lvTelephony = findViewById(R.id.lvTelephonyInfor);
        adapter = new TelephonyInforAdapter(this, R.layout.item_telephony_infor);
        lvTelephony.setAdapter(adapter);
    }

    public void directCall(TelephonyInfor ti) {
        tempTelephonyInforForCall = ti; // Lưu tạm thông tin để gọi sau khi cấp quyền
        checkAndRequestCallPermission(ti);
    }

    public void dialupCall(TelephonyInfor ti) {
        Uri uri = Uri.parse("tel:" + ti.getPhone());
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(uri);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(this, "Không tìm thấy ứng dụng điện thoại để quay số", Toast.LENGTH_SHORT).show();
        }
    }

    // --- Thêm Option Menu ---
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.telephony_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_all_contacts) {
            displayContacts(allContacts);
            Toast.makeText(this, "Hiển thị tất cả liên hệ", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.menu_viettel) {
            filterContactsByNetwork("Viettel");
            Toast.makeText(this, "Lọc khách hàng Viettel", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.menu_mobifone) {
            filterContactsByNetwork("Mobifone");
            Toast.makeText(this, "Lọc khách hàng Mobifone", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.menu_other_networks) {
            filterContactsByNetwork("Other");
            Toast.makeText(this, "Lọc khách hàng nhà mạng khác", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Phương thức lọc liên hệ theo nhà mạng
    private void filterContactsByNetwork(String network) {
        List<TelephonyInfor> filteredList = new ArrayList<>();
        for (TelephonyInfor contact : allContacts) {
            String phone = contact.getPhone(); // Số điện thoại đã được chuẩn hóa

            boolean isViettel = Pattern.matches(VIETTEL_REGEX, phone);
            boolean isMobifone = Pattern.matches(MOBIFONE_REGEX, phone);
            // Bạn có thể thêm các biến kiểm tra cho các nhà mạng khác nếu muốn lọc chi tiết
            // boolean isVinaphone = Pattern.matches(VINAPHONE_REGEX, phone);
            // boolean isVietnamobile = Pattern.matches(VIETNAMOBILE_REGEX, phone);
            // boolean isGmobile = Pattern.matches(GMOBILE_REGEX, phone);


            switch (network) {
                case "Viettel":
                    if (isViettel) {
                        filteredList.add(contact);
                    }
                    break;
                case "Mobifone":
                    if (isMobifone) {
                        filteredList.add(contact);
                    }
                    break;
                case "Other":
                    // Là nhà mạng khác nếu không khớp với bất kỳ nhà mạng nào đã định nghĩa
                    if (!isViettel && !isMobifone) { // Có thể mở rộng thêm !isVinaphone && ...
                        filteredList.add(contact);
                    }
                    break;
            }
        }
        displayContacts(filteredList);
        if (filteredList.isEmpty() && !network.equals("All")) {
            Toast.makeText(this, "Không tìm thấy liên hệ nào cho nhà mạng " + network, Toast.LENGTH_SHORT).show();
        }
    }

    // Phương thức cập nhật ListView với danh sách liên hệ mới
    private void displayContacts(List<TelephonyInfor> contactsToDisplay) {
        adapter.clear();
        adapter.addAll(contactsToDisplay);
        adapter.notifyDataSetChanged();
    }
}