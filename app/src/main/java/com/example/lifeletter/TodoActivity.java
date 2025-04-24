package com.example.lifeletter;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TodoActivity extends AppCompatActivity {

    private Button button_ret;
    private static final String PREFERENCES_NAME = "TodoPrefs";
    private static final String KEY_TODO_LIST = "TodoList";
    myDataHelpler myDataHelpler;
    ListView view_all;

    @Override
    protected void onRestart() {
        super.onRestart();
        ViewAll();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        myDataHelpler=new myDataHelpler(TodoActivity.this);
        // 初始化按钮
        button_ret = findViewById(R.id.Button_TodoRet);
        view_all =findViewById(R.id.list_todo);


         //设置按钮点击事件
        button_ret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 点击按钮后跳转到 LessonActivity
                finish();
            }
        });
//        String activityId="" ;
//        String activityName = "";
//        String activityTime="";
//        String activityLocation="";
        // 获取 Intent 中的数据
//        Intent intent = getIntent();
//        String receivedData = intent.getStringExtra("EXTRA_STRING"); // 使用相同的键获取数据
//
//        Pattern pattern=Pattern.compile("\\*活动编号\\*：(.*?)\\n\\*活动名称\\*：(.*?)\\n\\*活动时间\\*：(.*?)\\n\\*活动地点\\*：(.*)");
//
//        Matcher matcher= pattern.matcher(receivedData);
//        if(matcher.find()){
//             activityId = matcher.group(1);
//            activityName = matcher.group(2);
//             activityTime = matcher.group(3);
//             activityLocation = matcher.group(4);
//        }
//        TodoList todolist=new TodoList(-1,activityName,activityTime,activityLocation);
//
//         myDataHelpler=new myDataHelpler(TodoActivity.this);
//        String s =myDataHelpler.addOne(todolist);
//        //Toast.makeText(TodoActivity.this,"ADD: "+s,Toast.LENGTH_SHORT).show();
//        myDataHelpler=new myDataHelpler(TodoActivity.this);
       ViewAll();

        view_all.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TodoList todoList=(TodoList) parent.getItemAtPosition(position);

                AlertDialog.Builder dialog = new AlertDialog.Builder(TodoActivity.this);
                dialog.setTitle("请选择操作");
                dialog.setNegativeButton("删除", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       String s1= myDataHelpler.deleteOne(todoList);
//                        Toast.makeText(TodoActivity.this,"DELETE: "+s1,Toast.LENGTH_SHORT).show();
                      ViewAll();
                    }
                });
                dialog.setPositiveButton("修改", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent1 = new Intent(TodoActivity.this,UpdateActivity.class);
                        intent1.putExtra("id",todoList.getId());
                        intent1.putExtra("name",todoList.getName());
                        intent1.putExtra("time",todoList.getTime());

                        intent1.putExtra("location",todoList.getLocation());
                        startActivity(intent1);
                    }
                });

                dialog.create();
                dialog.show();
            }
        });

    }
//
    private void ViewAll() {
//        ArrayList<TodoList> tolist = new ArrayList<>(TodoActivity.this,)

        ListViewAdapter listViewAdapter = new ListViewAdapter(TodoActivity.this,R.layout.listview_item, (ArrayList<TodoList>) myDataHelpler.getall());
        view_all.setAdapter(listViewAdapter);
//        ArrayAdapter<TodoList> adapter =new ArrayAdapter<>(TodoActivity.this, android.R.layout.simple_list_item_1,myDataHelpler.getall());
//        view_all.setAdapter(adapter);
    }


}
