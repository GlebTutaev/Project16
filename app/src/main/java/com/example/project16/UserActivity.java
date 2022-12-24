package com.example.project16;

import static com.example.project16.UserStaticInfo.POSITION;
import static com.example.project16.UserStaticInfo.users;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class UserActivity extends AppCompatActivity {


    EditText NameTextView, StateTextView;
    private User activeUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        int position = getIntent().getIntExtra(POSITION,0);
        activeUser = users.get(position);
        Init();
        setUserInfo();
    }

    private void setUserInfo(){
        NameTextView.setText(activeUser.getName());
        StateTextView.setText(activeUser.getState());

    }

    private void Init(){
        NameTextView = findViewById(R.id.NameTextView);
        StateTextView = findViewById(R.id.StateTextView);
    }

    public void Back(){
        onBackPressed();
    }

    public void Save(View view) {
        activeUser.setName(NameTextView.getText().toString());
        activeUser.setState(StateTextView.getText().toString());
        MainActivity.UpdateList();
        finish();
    }
}