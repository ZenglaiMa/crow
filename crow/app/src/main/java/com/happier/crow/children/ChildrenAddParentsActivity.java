package com.happier.crow.children;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.happier.crow.R;
import com.happier.crow.constant.Constant;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ChildrenAddParentsActivity extends AppCompatActivity {
    private int cid;
    private String remark;
    private String phone;
    private Button btnSave;
    private EditText etFatherPhone;
    private EditText etMotherPhone;
    private int adderStatus = 1;
    private int isIce = 0;//子女添加联系人时，紧急联系人值为0
<<<<<<< HEAD
    public static final String ADDCONTACT_PATH = "/contact/addContact";

=======
    private List<Map<String,Object>> dataSource = new ArrayList<>();
    private Gson gson;
    public static final String CHECK_PATH = "/contact/checkParents";
    public static final String ADDPARENTS_PATH="/contact/addContact";
    public static final String SHOW_CONTACT_PATH = "/contact/showContacts";
>>>>>>> 9eca733f69e8623fb4946263ee962508627799fd
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_children_addparents);
        SharedPreferences sharedPreferences = getSharedPreferences("authid", MODE_PRIVATE);
        cid = sharedPreferences.getInt("cid", 0);

<<<<<<< HEAD
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < 1; i++) {
                    if (i == 0) {
                        remark = "父亲";
                        phone = etFatherPhone.getText().toString();
                        addContact(adderStatus, phone, remark);
                    } else if (i == 1) {
                        remark = "母亲";
                        phone = etMotherPhone.getText().toString();
                        addContact(adderStatus, phone, remark);
=======
        showParents();

       btnSave.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                for (int i = 0;i < 1;i++){
                    if (i==0){
                        remark = "父亲";
                        phone = etFatherPhone.getText().toString();
                        checkParent(adderStatus,phone,remark);
                    }else if(i == 1){
                        remark = "母亲";
                        phone = etMotherPhone.getText().toString();
                        checkParent(adderStatus,phone,remark);
>>>>>>> 9eca733f69e8623fb4946263ee962508627799fd
                    }
                }

            }
        });

    }

<<<<<<< HEAD
    private void addContact(int adderStatus, String phone, String remark) {
        OkHttpClient client = new OkHttpClient();
        FormBody body = new FormBody.Builder()
                .add("adderId", String.valueOf(cid))
                .add("adderStatus", String.valueOf(adderStatus))
                .add("phone", phone)
                .add("remark", remark)
                .add("isIce", String.valueOf(isIce))
                .build();
        Request request = new Request.Builder()
                .post(body)
                .url(Constant.BASE_URL + ADDCONTACT_PATH)
=======
    private void checkParent(final int adderStatus, final String phone, final String remark) {
        OkHttpClient client = new OkHttpClient();
        FormBody body = new FormBody.Builder()
                .add("adderId",String.valueOf(cid))
                .add("adderStatus",String.valueOf(adderStatus))
                .add("phone",phone)
                .build();
        Request request = new Request.Builder()
                .post(body)
                .url(Constant.BASE_URL+CHECK_PATH)
>>>>>>> 9eca733f69e8623fb4946263ee962508627799fd
                .build();

        Call call = client.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
<<<<<<< HEAD
                String tag = response.body().string();
                if (tag.equals("101")) {
                    Looper.prepare();
                    Toast.makeText(getApplicationContext(), "添加的父亲或母亲尚未注册，请先注册！", Toast.LENGTH_LONG).show();
                    Looper.loop();
                }
=======
                    String tag = response.body().string();
                    if(tag.equals("101")){
                        Looper.prepare();
                        Toast.makeText(getApplicationContext(),"添加的父亲或母亲尚未注册，请先注册！",Toast.LENGTH_LONG).show();
                        Looper.loop();
                    }else if(tag.equals("999")){
                        Looper.prepare();
                        Toast.makeText(getApplicationContext(),"添加成功！",Toast.LENGTH_LONG).show();
                        Looper.loop();
                    }else if(tag.equals("0")){
                        Looper.prepare();
                        Toast.makeText(getApplicationContext(),"已添加过父母信息！",Toast.LENGTH_LONG).show();
                        Looper.loop();
                    }
                    else if(tag.equals("11")){
                        addParent(adderStatus,phone,remark);
                    }
>>>>>>> 9eca733f69e8623fb4946263ee962508627799fd
            }
        });
    }

    private void addParent(int adderStatus, String phone, final String remark) {
        OkHttpClient client = new OkHttpClient();
        FormBody body = new FormBody.Builder()
                .add("adderId",String.valueOf(cid))
                .add("adderStatus",String.valueOf(adderStatus))
                .add("phone",phone)
                .add("remark",remark)
                .add("isIce",String.valueOf(isIce))
                .build();
        Request request = new Request.Builder()
                .post(body)
                .url(Constant.BASE_URL+ADDPARENTS_PATH)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String tag = response.body().string();
                if (tag.equals("1")){
                    Looper.prepare();
                    Toast.makeText(getApplicationContext(),"添加成功",Toast.LENGTH_SHORT).show();
                    Looper.loop();
                }
            }
        });
    }

    public void showParents(){
        OkHttpClient client = new OkHttpClient();
        FormBody body = new FormBody.Builder()
                .add("adderId",String.valueOf(cid))
                .build();
        Request request = new Request.Builder()
                .post(body)
                .url(Constant.BASE_URL+SHOW_CONTACT_PATH)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String data = response.body().string();
            }
        });
    }

    private void initData(String data) {
        try{
            gson = new Gson();
            Type type = new TypeToken<List<Map<String,Object>>>(){}.getType();
            dataSource = gson.fromJson(data,type);
            Map<String,Object> map1 = dataSource.get(0);
            etFatherPhone.setText(map1.get("phone").toString());
            Map<String,Object> map2 = dataSource.get(1);
            etMotherPhone.setText(map2.get("phone").toString());
            Log.e("dataSource",dataSource.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
