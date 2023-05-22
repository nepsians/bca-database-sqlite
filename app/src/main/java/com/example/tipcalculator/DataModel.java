package com.example.tipcalculator;

public class DataModel {
    int id;
    String name;
    String address;

    public DataModel(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getAddress() {
        return this.address;
    }
}
