package com.example.samir.navigationtest;

import java.util.ArrayList;

/**
 * Created by zsami on 24-Dec-16.
 */


public class SingletonContainer {
    private static SingletonContainer instance = null;

    public static ArrayList<String> getList() {
        return list;
    }

    public static void setList(ArrayList<String> list) {
        SingletonContainer.list = list;
    }

    private static ArrayList<String> list;
    protected SingletonContainer() {
        // Exists only to defeat instantiation.
    }

    public static SingletonContainer getInstance() {
        if(instance == null) {
            instance = new SingletonContainer();
        }
        return instance;
    }
}
