package com.example.taskmanager;

public class Color {

    static int count = 0;
    int hexValue;
    String name;

    String hexString;
    Color(int hexValue, String hexString, String name)
    {
        this.hexValue = hexValue;
        this.name = name;
        count++;
    }
}
