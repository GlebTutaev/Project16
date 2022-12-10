package com.example.project16;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    Context context;
    LayoutInflater layoutInflater;
    List<User> users = new ArrayList<>();
    UserListAdapter userListAdapter;
    FrameLayout userPanel;
    TextView nameTV, stateTV, ageTV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AddUsersInList();
        Init();
    }

    private void AddUsersInList() {

        users.add(new User("Иван", "Я усталь", 19));
        users.add(new User("Иван", "Я усталь", 19));
        users.add(new User("Иван", "Я усталь", 19));
        users.add(new User("Иван", "Я усталь", 19));
    }

    private void Init() {
        listView = findViewById(R.id.listView);
        context = this;
        layoutInflater = layoutInflater.from(context);
        UserListAdapter userLisAdapter = new UserListAdapter();

        userPanel = findViewById(R.id.userPanel);
        nameTV = findViewById(R.id.nameTV);
        stateTV = findViewById(R.id.stateTV);
        ageTV = findViewById(R.id.ageTV);
    }

    public void BackToList(View view) {
        UserVisibility(false);
    }

    private void UserVisibility(boolean b) {

        if(b) userPanel.setVisibility(View.VISIBLE);
        else userPanel.setVisibility(View.GONE);
    }


    private class UserListAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return users.size();
        }

        @Override
        public User getItem(int position) {
            return users.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }



        @Override
        public View getView(int position, View currentView, ViewGroup parent) {


            User currentUser  = getItem(position);
            currentView = layoutInflater.inflate(R.layout.item_user, parent, false);

            TextView nameView = currentView.findViewById(R.id.nameTV);
            TextView stateView = currentView.findViewById(R.id.stateTV);

            nameView.setText(currentUser.getName());
            stateView.setText(currentUser.getState());

            currentView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    InitPanel(getItem(position));
                    UserVisibility(true);
                }
            });

            return currentView;
        }

    }

    private void InitPanel(User item) {

        nameTV.setText(item.getName());
        stateTV.setText(item.getState());
        ageTV.setText(String.valueOf(item.getAge()));
    }
}