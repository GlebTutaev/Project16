package com.example.project16;

import static com.example.project16.Transform.parsIntOrDefault;
import static com.example.project16.UserStaticInfo.POSITION;
import static com.example.project16.UserStaticInfo.users;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class UserActivity extends AppCompatActivity {


    EditText NameTextView, StateTextView, AgeTextView;
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
        AgeTextView.setText(String.valueOf(activeUser.getAge()));

    }

    private void Init(){
        NameTextView = findViewById(R.id.NameTextView);
        StateTextView = findViewById(R.id.StateTextView);
        AgeTextView = findViewById(R.id.AgeTextView);
    }

    public void Back(View view) {
        onBackPressed();
    }

    public void Save(View view) {
        activeUser.setName(NameTextView.getText().toString());
        activeUser.setState(StateTextView.getText().toString());
        activeUser.setAge(Integer.parseInt(AgeTextView.getText().toString()));
        String age = AgeTextView.getText().toString();

        activeUser.setAge(parsIntOrDefault(age,activeUser.getAge()));
        MainActivity.UpdateListAndUserPanel(activeUser);
        finish();
    }


}