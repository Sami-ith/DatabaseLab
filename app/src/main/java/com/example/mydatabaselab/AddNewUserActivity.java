package com.example.mydatabaselab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextDirectionHeuristics;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mydatabaselab.db.AppDatabase;
import com.example.mydatabaselab.db.User;

public class AddNewUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_user);
        final EditText firstName=findViewById(R.id.edt_first_name);
        final EditText lastName=findViewById(R.id.edt_last_name);
        Button btnSave=findViewById(R.id.btn_save);

        btnSave.setOnClickListener(v-> {
                Intent replyIntent = new Intent();
                if(TextUtils.isEmpty(firstName.getText().toString())|| TextUtils.isEmpty(lastName.getText().toString())){
                    setResult(RESULT_CANCELED, replyIntent);
                }else{
                    saveNewUser(firstName.getText().toString(),lastName.getText().toString());
                    setResult(RESULT_CANCELED, replyIntent);
                }
            });
    }

    private void saveNewUser(String fName,String lName) {
        AppDatabase db=AppDatabase.getINSTANCE(this.getApplicationContext());
        User user=new User();
        user.firstName=fName;
        user.lastName=lName;
        db.userDao().insert(user);
        finish();

    }
}
