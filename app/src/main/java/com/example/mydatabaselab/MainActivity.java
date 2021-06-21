package com.example.mydatabaselab;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mydatabaselab.db.AppDatabase;
import com.example.mydatabaselab.db.User;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private UserListAdaptor userListAdaptor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnAdd=findViewById(R.id.btnAdd);
        initRecyclerView();
        loadUserList();
        ActivityResultLauncher<Intent> startActivityForResult = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (AppCompatActivity.RESULT_OK==-1) {
                        loadUserList();
                    }
                }
        );
        btnAdd.setOnClickListener(v-> {
                Intent intent = new Intent(MainActivity.this,AddNewUserActivity.class);
                startActivityForResult.launch(intent);

            });
    }

    private void initRecyclerView() {
        RecyclerView recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        userListAdaptor=new UserListAdaptor(this);
        recyclerView.setAdapter(userListAdaptor);
    }

    private void loadUserList() {
        AppDatabase db=AppDatabase.getINSTANCE(this.getApplicationContext());
        List<User> userList=db.userDao().getAllUsers();
        userListAdaptor.setUserList(userList);
    }
}
