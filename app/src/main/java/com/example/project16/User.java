package com.example.project16;

public class User {

    private String Name, State;
    private int Age;
    private int StateSignal;


    public User(String name, String state, int age, int stateSignal) {

        Name = name;
        Age = age;
        State = state;
        StateSignal = stateSignal;
    }


    public int getStateSignal() {
        return StateSignal;
    }

    public void setStateSignal(int stateSignal) {
        StateSignal = stateSignal;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }
}
