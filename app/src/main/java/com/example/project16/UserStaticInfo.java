package com.example.project16;

import java.util.ArrayList;
import java.util.List;

public class UserStaticInfo {

    public final static String USER_SIGN_IN_INFO = "UserSignInInfo";
    public final static String USER_PROFILE_INFO = "UserProfileInfo";
    public final static String PASSWORD = "password";
    public final static String PROFILE_ID = "profileId";
    public final static String NAME = "name";
    public final static String AGE = "age";
    public final static String STATE = "state";
    public static String profileId;



    public final static String POSITION = "position";
    public static List<User> users = new ArrayList<>();

    public UserStaticInfo() {
        if(users.size()==0){
            AddUsersInList();
        }
    }

    private void AddUsersInList() {

        users.add(new User("Иван", "Я усталь", 19, 0));
        users.add(new User("Иван", "Я не усталь", 19,1 ));
        users.add(new User("Иван", "Я Игорь", 19,2 ));
        users.add(new User("Иван", "Я ", 19,0 ));
    }
}
