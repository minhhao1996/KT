package com.example.mypc.danhba;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
// khởi tạo các giá trị từ main truyền vào
// context là activity từ maint
//reourece là layout custom truyen vào.
// arr danh sach dữ liệu contact truyen vao.
public class ContactAdapter extends ArrayAdapter<Contact> {
    private  Context context;
    private int resource;
    private List<Contact>arrayContact;
    public ContactAdapter(@NonNull Context context, int resource, @NonNull List<Contact> objects) {
        super(context, resource, objects);
        this.context= context;
        this.resource = resource;
        this.arrayContact=objects;

    }
// getview hàm dùng dể custum layout.
    //converview xử lý item.
    // paren danh sach dữ liệu truyen từ main
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if(convertView==null){
            // Gọi layoutInflater ra để bắt đầu ánh xạ view và data.
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list,parent,false);
            viewHolder.avatar = (ImageView) convertView.findViewById(R.id.img_avatar);
            viewHolder.ten = (TextView) convertView.findViewById(R.id.tvten);
            viewHolder.sdt = (TextView) convertView.findViewById(R.id.tvsdt);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Contact contact = arrayContact.get(position);
        viewHolder.ten.setText(contact.getName());
        viewHolder.sdt.setText(contact.getNumber());

        if(contact.isMale()){
            viewHolder.avatar.setBackgroundResource(R.drawable.nam);
        }else {
            viewHolder.avatar.setBackgroundResource(R.drawable.girl);
        }

        return convertView;
    }
    // giúp giảm lag khi kéo xuống dưới màn hình ,vì khỏi khởi tạo ,chỉ lấy ra thôi
    public class ViewHolder{
        ImageView avatar ;
        TextView ten,sdt;
    }
}
