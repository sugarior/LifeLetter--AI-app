package com.example.lifeletter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AskActivity extends AppCompatActivity {

    private EditText editText;
    private TextView txtResponse;
    private Button Button_Return;
    private Button Button_send;
    private ExecutorService executor; // 用于在后台线程执行网络请求
    myDataHelpler myDataHelpler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ask);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.ask), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        myDataHelpler=new myDataHelpler(AskActivity.this);
        Button_Return = findViewById(R.id.btn_back);
        Button_Return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        txtResponse = findViewById(R.id.TV_respond);
        editText = findViewById(R.id.Edit_Ask);
        Button_send = findViewById(R.id.btn_confirm);
        Button_send.setOnClickListener(new View.OnClickListener() {

            String todoAdd;
            @Override
            public void onClick(View v) {
                todoAdd="*活动编号*：1\n*活动名称*：交互设计\n*活动时间*：12月25日\n*活动地点*：学武楼206*";
                String inputText = editText.getText().toString();
                // 使用 ExecutorService 在后台线程中执行网络请求
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        String response = HttpHelper.sendToRobot(inputText);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                todoAdd=response;
                            }

                        });
                        txtResponse.setText(todoAdd);
//                        Intent intent = new Intent(AskActivity.this, TodoActivity.class);
//                        intent.putExtra("EXTRA_STRING", response); // 使用一个键将字符串放入Intent
//                        startActivity(intent);
                        String activityId="" ;
                        String activityName = "";
                        String activityTime="";
                        String activityLocation="";
                        // 获取 Intent 中的数据
//                        Intent intent = getIntent();
//                        String receivedData = intent.getStringExtra("EXTRA_STRING"); // 使用相同的键获取数据
//                        todoAdd="*活动编号*：1\n*活动名称*：交互设计\n*活动时间*：12月25日\n*活动地点*：学武楼206*";
                        Pattern pattern=Pattern.compile("\\*活动编号\\*：(.*?)\\n\\*活动名称\\*：(.*?)\\n\\*活动时间\\*：(.*?)\\n\\*活动地点\\*：(.*)");

                        Matcher matcher= pattern.matcher(todoAdd);
                        if(matcher.find()){
                            activityId = matcher.group(1);
                            activityName = matcher.group(2);
                            activityTime = matcher.group(3);
                            activityLocation = matcher.group(4);
                        }
                        TodoList todolist=new TodoList(-1,activityName,activityTime,activityLocation);

                        String s =myDataHelpler.addOne(todolist);
//                        Toast.makeText(AskActivity.this,"ADD: "+s,Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AskActivity.this, TodoActivity.class);
                        startActivity(intent);
                    }
                });
            }
        });

        executor = Executors.newSingleThreadExecutor(); // 初始化 ExecutorService
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (executor != null) {
            executor.shutdown(); // 在 Activity 销毁时关闭 ExecutorService
        }
    }
}