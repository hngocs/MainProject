package com.hngocs.mainproject.connectors;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.hngocs.mainproject.models.PaymentMethod;
import java.util.ArrayList;
import java.util.List;

public class PaymentMethodConnector {

    // Phương thức lấy tất cả các phương thức thanh toán từ SQLite
    public List<PaymentMethod> getAllPaymentMethods(SQLiteDatabase database) {
        List<PaymentMethod> paymentMethods = new ArrayList<>();
        Cursor cursor = null;
        try {
            // Đảm bảo tên bảng và cột đúng với CREATE TABLE của bạn
            cursor = database.rawQuery("SELECT Id, Name, Description FROM PaymentMethod", null);

            while (cursor.moveToNext()) {
                int id = cursor.getInt(0); // Cột 0 là Id
                String name = cursor.getString(1); // Cột 1 là Name
                String description = cursor.getString(2); // Cột 2 là Description
                paymentMethods.add(new PaymentMethod(id, name, description));
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Ghi log hoặc hiển thị Toast lỗi nếu cần thiết
            // Toast.makeText(context, "Lỗi đọc dữ liệu: " + e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            // KHÔNG đóng database ở đây. Database sẽ được đóng bởi SQLiteConnector.closeDatabase()
        }
        return paymentMethods;
    }

    // Tùy chọn: Phương thức thêm mới PaymentMethod
    public boolean addPaymentMethod(SQLiteDatabase database, PaymentMethod pm) {
        try {
            ContentValues values = new ContentValues();
            values.put("Name", pm.getName());
            values.put("Description", pm.getDescription());
            long result = database.insert("PaymentMethod", null, values);
            return result != -1; // Trả về true nếu thêm thành công
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Tùy chọn: Phương thức kiểm tra sự tồn tại (ví dụ theo tên)
    public boolean isExist(SQLiteDatabase database, String name) {
        Cursor cursor = null;
        try {
            cursor = database.rawQuery("SELECT COUNT(*) FROM PaymentMethod WHERE Name = ?", new String[]{name});
            if (cursor.moveToFirst()) {
                return cursor.getInt(0) > 0;
            }
            return false;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
}
