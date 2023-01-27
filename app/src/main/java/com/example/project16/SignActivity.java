package com.example.project16;

import static com.example.project16.Transform.StringNoNull;
import static com.example.project16.Transform.Vibrate;
import static com.example.project16.UserStaticInfo.AGE;
import static com.example.project16.UserStaticInfo.NAME;
import static com.example.project16.UserStaticInfo.PASSWORD;
import static com.example.project16.UserStaticInfo.PROFILE_ID;
import static com.example.project16.UserStaticInfo.STATE;
import static com.example.project16.UserStaticInfo.USERS_PROFILE_INFO;
import static com.example.project16.UserStaticInfo.USERS_SIGN_IN_INFO;
import static com.example.project16.UserStaticInfo.profileId;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignActivity extends AppCompatActivity {


    private EditText LoginTextView, PasswordTextView,
            NewLoginTextView,NewPasswordTextView,NewAgeTextView,NewNameTextView,NewStateTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        Init();
    }

    private void Init() {

        TabHost tabHost = findViewById(R.id.tabHost);
        tabHost.setup();

        TabHost.TabSpec tabSpec = tabHost.newTabSpec("tag1");
        tabSpec.setContent(R.id.tabSignIn);
        tabSpec.setIndicator("Вход");

        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("tag2");
        tabSpec.setContent(R.id.tabSignUp);
        tabSpec.setIndicator("Регистрация");

        tabHost.addTab(tabSpec);
        tabHost.setCurrentTab(0);

        LoginTextView = findViewById(R.id.LoginTextView);
        PasswordTextView = findViewById(R.id.PasswordTextView);
        NewLoginTextView = findViewById(R.id.NewLoginTextView);
        NewPasswordTextView = findViewById(R.id.NewPasswordTextView);
        NewAgeTextView = findViewById(R.id.NewAgeTextView);
        NewNameTextView = findViewById(R.id.NewNameTextView);
        NewStateTextView = findViewById(R.id.NewStateTextView);

    }
    private String getNewLogin() {return NewLoginTextView.getText().toString();}
    private String getNewPassword() {return NewPasswordTextView.getText().toString();}
    private int getNewAge() {
        try {
            return Transform.parsIntOrDefault(NewAgeTextView.getText().toString(),0);
        } catch (Exception NumberFormatException){
            return 0;
        }
    }
    private String getNewName() {return NewNameTextView.getText().toString();}
    private String getNewState() {return NewStateTextView.getText().toString();}

    public void SignIn(View view){

        if(StringNoNull(getPassword())&& StringNoNull(getLogin()))
        {

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference(USERS_SIGN_IN_INFO);
            myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot){
                    String login = getLogin();
                    Object value = dataSnapshot.child(login).child(PASSWORD).getValue();
                    if(value!=null)
                    {
                        if(value.toString().equals(getPassword()))
                        {
                            goNext(dataSnapshot.child(login).child(PROFILE_ID).getValue().toString());}
                        else CantSignIn();
                    }
                    else CantSignIn();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        else{
            Vibrate(SignActivity.this);
            Toast.makeText(this,
                    getResources().getText(R.string.NullParametersMessage),
                    Toast.LENGTH_SHORT).show();
        }


    }

    private String getLogin() {
        return LoginTextView.getText().toString();
    }

    private void CantSignIn() {
        Toast.makeText(SignActivity.this, getResources().getText(R.string.CanSignInMessage),
                Toast.LENGTH_SHORT).show();
    }

    private void goNext(String profileId) {

        UserStaticInfo.profileId = profileId;

    }
    public void SignUp(View view){

        if(StringNoNull(getNewPassword())&& StringNoNull(getNewLogin()))
        {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            String id = database.getReference(USERS_PROFILE_INFO).push().getKey();
            String login = getNewLogin();

            database.getReference(USERS_SIGN_IN_INFO).child(login).child(PASSWORD).setValue(getNewPassword());
            database.getReference(USERS_SIGN_IN_INFO).child(login).child(PROFILE_ID).setValue(id);

            database.getReference(USERS_PROFILE_INFO).child(id).child(AGE).setValue(getNewAge());
            database.getReference(USERS_PROFILE_INFO).child(id).child(NAME).setValue(getNewName());
            database.getReference(USERS_PROFILE_INFO).child(id).child(STATE).setValue(getNewState());

            goNext(id);

        }
        else{
            Vibrate(SignActivity.this);
            Toast.makeText(this,
                    getResources().getText(R.string.NullParametersMessage),
                    Toast.LENGTH_SHORT).show();
        }
    }

    private String getPassword() {
        return PasswordTextView.getText().toString();
    }
}