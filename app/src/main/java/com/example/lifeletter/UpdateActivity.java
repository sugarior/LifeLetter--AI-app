package com.example.lifeletter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class UpdateActivity extends AppCompatActivity {

    EditText update_name,update_time,update_location;
    Button update_btn;
    Button btnret;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnret=findViewById(R.id.button_return_lesson);

        btnret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent =getIntent();
        int id =intent.getIntExtra("id",0);
        String name=intent.getStringExtra("name");
        String time= intent.getStringExtra("time");
        String location =intent.getStringExtra("location");

        update_btn=findViewById(R.id.btn_update);
        update_name =findViewById(R.id.update_name);
        update_time=findViewById(R.id.update_time);
        update_location=findViewById(R.id.update_location);

        update_name.setText(name);
        update_time.setText(time);
        update_location.setText(location);

        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TodoList todolist=new TodoList(id,update_name.getText().toString(),update_time.getText().toString(),update_location.getText().toString());
                myDataHelpler mydb= new myDataHelpler(UpdateActivity.this);
                mydb.updateOne(todolist);
                finish();

            }
        });
    }
}