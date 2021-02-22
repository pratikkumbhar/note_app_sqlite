package com.example.mynotesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mynotesapp.Database.DatabaseHelper;
import com.example.mynotesapp.Database.Holder;
import com.example.mynotesapp.Database.listAdaptor;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText seachbar_edittext;
    FloatingActionButton floatingActionButton;
   RecyclerView recyclerView;
    listAdaptor adaptor;
   DatabaseHelper databaseHelper;
   Holder holder;
   ImageView imageView;
   public static final int REQUEST_UPADATE_CODE = 1;
   private listAdaptor.OnNoteListener onNoteListener;
    List arrayList;
    int onNoteposition = -1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.resyscle);
        recyclerView.setHasFixedSize(true);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);

        imageView = (ImageView)findViewById(R.id.setting_icon);
        databaseHelper = new DatabaseHelper(MainActivity.this);
        floatingActionButton = (FloatingActionButton)findViewById(R.id.floatingActionButton);
        Showalldata();


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CreateNote.class);
                startActivity(intent);
            }
        });



        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Setting.class);
                startActivity(intent);
            }
        });


        recyclerView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                imageView.setImageResource(R.drawable.delete_icon);
                v.setBackgroundColor(getResources().getColor(R.color.delete_color));

                return true;
            }
        });



    }


    public void Showalldata()
    {
        setOnclickListner();
       arrayList = databaseHelper.getAllData();
        adaptor = new listAdaptor(arrayList, onNoteListener);
        recyclerView.setAdapter(adaptor);

    }

    private void setOnclickListner() {
        onNoteListener = new listAdaptor.OnNoteListener() {
            @Override
            public void onNotechick(View v, int position) {
                onNoteposition = position;
                holder = (Holder) arrayList.get(position);
                String title,note;
                title = holder.getTitle();
                note = holder.getNote();
                Intent intent = new Intent(getApplicationContext(),CreateNote.class);
                intent.putExtra("position",position);
                intent.putExtra("title",title);
                intent.putExtra("note",note);
                intent.putExtra("note1",true);

                startActivity(intent);
            }
        };
    }




}