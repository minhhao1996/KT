package com.example.mypc.danhba;


import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;
import android.support.v7.app.AlertDialog;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private List<Contact> arrayContact;
    private ContactAdapter adapter;
    private ListView lvDanhSach;
    private EditText editTen,editSdt;
    private RadioButton radioNam,radioNu;
    private Button btnThem,btnxoa,btnhieuchinh;
    private int vitri = -1;


    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference=database.getReference();
    DatabaseReference reference_DanhBa = reference.child("ThongTin");





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhxa();
        arrayContact= new ArrayList<>();
        adapter = new ContactAdapter(this,R.layout.item_list,arrayContact);
        lvDanhSach.setAdapter(adapter);

        ///////////////////////////////////////////////////////////////
       lvDanhSach.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
           @Override
           public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
               showThongBao();
               return false;
           }
       });
        lvDanhSach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Contact c = arrayContact.get(position);
                editTen.setText(c.getName());
                editSdt.setText(c.getNumber());

                vitri = position;

            }
        });

        btnxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xoadanhba();

            }
        });
        btnhieuchinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hieuchinh();

            }
        });




    firebase();

    }



    private  void  anhxa(){
        editTen= (EditText) findViewById(R.id.editTen);
        editSdt= (EditText) findViewById(R.id.editSdt);
        radioNam = (RadioButton) findViewById(R.id.rdNam);
        radioNu = (RadioButton) findViewById(R.id.rdNu);
        lvDanhSach = (ListView) findViewById(R.id.lvDanhBa);
        btnThem = (Button) findViewById(R.id.btnAdd);
        btnhieuchinh = (Button) findViewById(R.id.btnsua);
        btnxoa = (Button) findViewById(R.id.btnxoa);


    }

    public void add_contact(View view){
        if(view.getId()==R.id.btnAdd){
          String name = editTen.getText().toString().trim();
          String number = editSdt.getText().toString().trim();
          boolean isMale = true;

          if(radioNam.isChecked()){
              isMale = true;
          }else {
              isMale = false;
          }
          if(TextUtils.isEmpty(name)||TextUtils.isEmpty(number)){
                Toast.makeText(this, "Vui long nhap du lieu", Toast.LENGTH_SHORT).show();
            }
            else {
              Contact contact = new Contact(name,number,isMale);
              //arrayContact.add(contact);
                reference_DanhBa.push().setValue(contact);
                editTen.setText("");
                editSdt.setText("");
          }
            adapter.notifyDataSetChanged();
            Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show();
        }

    }
    public void xoadanhba(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("MINHHAO.COM");
        builder.setMessage("Bạn có muốn xóa danh bạ không ?");
        builder.setCancelable(false);
        builder.setPositiveButton("Đồng Ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                arrayContact.remove(vitri);
                adapter.notifyDataSetChanged();

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
    public  void hieuchinh(){

      //  arrayContact.set(vitri,editTen.getText().toString(),editSdt.getText().toString(),radioNam);
    }

public void showThongBao(){
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setTitle("DANHBA.COM");
    builder.setMessage("Bạn có muốn gọi điện or nhắn tin ?");
    builder.setCancelable(false);
    builder.setPositiveButton("CALLL", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            Toast.makeText(MainActivity.this, "Calll", Toast.LENGTH_SHORT).show();
        }
    });
    builder.setNegativeButton("MESSAGER", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            Toast.makeText(MainActivity.this, "Send Mesenger", Toast.LENGTH_SHORT).show();
        }
    });
    AlertDialog alertDialog = builder.create();
    alertDialog.show();

}


   public void firebase() {
       reference_DanhBa.addChildEventListener(new ChildEventListener() {
           @Override
           public void onChildAdded(DataSnapshot dataSnapshot, String s) {
               Contact contact = dataSnapshot.getValue(Contact.class);
               arrayContact.add(contact);
               adapter.notifyDataSetChanged();
           }

           @Override
           public void onChildChanged(DataSnapshot dataSnapshot, String s) {
               Contact contact = dataSnapshot.getValue(Contact.class);
               arrayContact.add(contact);
               adapter.notifyDataSetChanged();

           }

           @Override
           public void onChildRemoved(DataSnapshot dataSnapshot) {

           }

           @Override
           public void onChildMoved(DataSnapshot dataSnapshot, String s) {

           }

           @Override
           public void onCancelled(DatabaseError databaseError) {

           }

       });
   }


}
