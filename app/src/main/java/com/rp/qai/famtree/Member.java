package com.rp.qai.famtree;

import java.io.Serializable;

public class Member implements Serializable {
    private int id;
    private String name;
    private String relation;
    private String title;
    private String address;
    private int number;


    public Member(String name, String relation, String title, String address, int number) {
        this.name = name;
        this.relation = relation;
        this.title = title;
        this.address = address;
        this.number = number;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
