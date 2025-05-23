package com.example.lifeletter;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class LessonActivity extends AppCompatActivity {

    private Button btlsret,btlask,btltodo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lesson);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.lesson), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btlsret=findViewById(R.id.button_lesson_return);
        btlsret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btlask=findViewById(R.id.btn_add);
        btlask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent askToFunc=new Intent(LessonActivity.this, AskActivity.class);
                startActivity(askToFunc);
            }
        });
        btltodo=findViewById(R.id.btn_todo);
        btltodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_todo=new Intent(LessonActivity.this,TodoActivity.class);
                startActivity(intent_todo);
            }
        });
        framework();

    }
    public void framework() {

        //创建一个GridLayout对象
        GridLayout gridLayout;
        //定义每个框的id，之后会动态改变id值
        int id = 1;

        //渲染每一列（周）
        for (int i = 1; i < 8; i++) {

            //注入GridLayout对应的列，根据星期几调用LayoutColumn方法
            gridLayout = LayoutColumn(i);

            //渲染每一行（节课）
            for (int j = 1; j < 10; j +=2) {
                //声明一个新的TextView
                TextView textView1 = new TextView(this);

                //给TextView设置style样式
                textView1.setId(id++);
                textView1.setText("");
                textView1.setMaxLines(5);
                textView1.setEllipsize(TextUtils.TruncateAt.END);
                textView1.setBackgroundColor(Color.parseColor("#F0FFFF"));
                textView1.setGravity(Gravity.CENTER);

                //GridLayout.LayoutParams设置在此gridLayout列中TextView布局
                GridLayout.LayoutParams params1 = new GridLayout.LayoutParams();
                params1.setMargins(5,10,5,10);
                params1.width = GridLayout.LayoutParams.MATCH_PARENT;
                params1.height = 0;
                //设置在gridLayout中的方位，参数1：在第几行。参数2：占几行。参数3：权值
                //这个权值是根据xml中第一个gridLayout节课权值设定的。
                params1.rowSpec = GridLayout.spec( j, 2,1);

                //把TextView和布局样式添加到此gridLayout中
                gridLayout.addView(textView1, params1);
            }

        }

    }

    public GridLayout LayoutColumn(int i) {

        //防止空指针操作
        GridLayout gridLayout = findViewById(R.id.d1);

        //参数i：星期几。根据i寻找xml对应的GridLayout并注入。
        switch (i) {
            case 1: {
                gridLayout = findViewById(R.id.d1);
                break;
            }
            case 2: {
                gridLayout = findViewById(R.id.d2);
                break;
            }
            case 3: {
                gridLayout = findViewById(R.id.d3);
                break;
            }
            case 4: {
                gridLayout = findViewById(R.id.d4);
                break;
            }
            case 5: {
                gridLayout = findViewById(R.id.d5);
                break;
            }
            case 6: {
                gridLayout = findViewById(R.id.d6);
                break;
            }
            case 7: {
                gridLayout = findViewById(R.id.d7);
                break;
            }
        }
        return gridLayout;
    }


}