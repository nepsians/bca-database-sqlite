package com.example.tipcalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    MyDbHelper myDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDbHelper = new MyDbHelper(this);

        EditText idEt = findViewById(R.id.idEt);
        EditText nameEt = findViewById(R.id.nameEt);
        EditText addressEt = findViewById(R.id.addressEt);
        Button insertBtn = findViewById(R.id.insertBtn);
        Button selectBtn = findViewById(R.id.selectBtn);
        Button updateBtn = findViewById(R.id.updateBtn);
        Button deleteBtn = findViewById(R.id.deleteBtn);
//        TextView result = findViewById(R.id.resultTv);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);

        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = Integer.parseInt(idEt.getText().toString());
                String name = nameEt.getText().toString();
                String address = addressEt.getText().toString();
                myDbHelper.insertData(id, name, address);

                Toast.makeText(getApplicationContext(), "Data inserted successfully!!!", Toast.LENGTH_LONG).show();
            }
        });

        selectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<DataModel> dataModels = new ArrayList<>();
                Cursor cursor = myDbHelper.selectData();
                while (cursor.moveToNext()) {
                    DataModel data = new DataModel(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
                    dataModels.add(data);
                }
                String data = "";
                for (int i = 0; i < dataModels.size(); i++) {
                    data += "Id=" + dataModels.get(i).getId() + "\t Name=" + dataModels.get(i).getName() + "\t Address=" + dataModels.get(i).getAddress() + "\n\n";
                }

//                result.setText(data);
                MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(MainActivity.this,
                        dataModels);
                recyclerView.setAdapter(adapter);
            }
        });

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = idEt.getText().toString();
                String name = nameEt.getText().toString();
                String address = addressEt.getText().toString();
                myDbHelper.updateData(id, name, address);

                Toast.makeText(getApplicationContext(), "Data updated successfully!!!", Toast.LENGTH_LONG).show();
            }
        });


        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = idEt.getText().toString();
                myDbHelper.deleteData(id);

                Toast.makeText(getApplicationContext(), "Data deleted successfully!!!", Toast.LENGTH_LONG).show();
            }
        });
    }
}

//selectBtn.setOnClickListener(new View.OnClickListener() {
//@Override
//public void onClick(View view) {
//        ArrayList<Integer> ids = new ArrayList<Integer>();
//        ArrayList<String> names = new ArrayList<String>(), address = new ArrayList<String>();
//        Log.d("asdfasdf: ", ids.toString());
//
//        Cursor cursor = myDbHelper.selectData();
//        while (cursor.moveToNext()) {
//        ids.add(cursor.getInt(0));
//        names.add(cursor.getString(1));
//        address.add(cursor.getString(2));
//        }
//        String data = "";
//        for (int i = 0; i < ids.size(); i++) {
//        data += "Id=" + ids.get(i) + "\t Name=" + names.get(i) + "\t Address=" + address.get(i) + "\n\n";
//        }
//        result.setText(data);
//        }
//        });