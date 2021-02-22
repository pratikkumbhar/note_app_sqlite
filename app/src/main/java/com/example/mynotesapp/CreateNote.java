package com.example.mynotesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mynotesapp.Database.DatabaseHelper;
import com.example.mynotesapp.Database.Holder;

public class CreateNote extends AppCompatActivity {
EditText title,note;
ImageView saveButton,deletebutton;
DatabaseHelper databaseHelper;
    Holder holder;
    
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        title = (EditText)findViewById(R.id.titleedit);
        note = (EditText)findViewById(R.id.noteedit);
        saveButton = (ImageView)findViewById(R.id.savebutton);
        deletebutton = (ImageView)findViewById(R.id.delete_create_icon);
        databaseHelper = new DatabaseHelper(CreateNote.this);

         position= getIntent().getIntExtra("position",-1);

        boolean isUpdate = getIntent().getBooleanExtra("note1",false);


        if(isUpdate)
        {
            editoldData();
        }
        else {
            savenewData();
        }

        deletebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder = new Holder(position);
                final boolean b = databaseHelper.deleteData(holder);
                if (b)
                    {
                        Toast.makeText(CreateNote.this,"Delete Sucess",Toast.LENGTH_SHORT).show();
                    }
                else
                {
                    Toast.makeText(CreateNote.this,"Error",Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });



    }
    public void savenewData()
    {
        saveButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {


        String titletext = title.getText().toString();
        titletext = titletext + "\n";
        String notetext = note.getText().toString();
        try {
            holder = new Holder(titletext, notetext);
        }
        catch (Exception e)
        {
            holder = new Holder( "error", "Noteis not saved");
        }

        final boolean b = databaseHelper.insertData(holder);
        if(b)
        {
            Toast.makeText(CreateNote.this,"Data Saved",Toast.LENGTH_SHORT).show();
        }
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
            }
        });

    }
    public void editoldData()
    {
       String mtitle,mnote;
       mtitle = getIntent().getStringExtra("title");
       mnote = getIntent().getStringExtra("note");

        title.setText(mtitle);
        note.setText(mnote);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gettitle = String.valueOf(title.getText());
                String getnote = String.valueOf(title.getText());
                holder = new Holder(position,gettitle,getnote);
                final boolean b = databaseHelper.updateData(holder);
                if(b)
                {
                    Toast.makeText(CreateNote.this,"Sucess",Toast.LENGTH_SHORT).show();

                }
                else{

                    Toast.makeText(CreateNote.this,"Error",Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);

            }
        });

    }
}