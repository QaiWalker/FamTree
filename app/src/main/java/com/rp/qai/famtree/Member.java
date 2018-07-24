package com.rp.qai.famtree;

import java.io.Serializable;

public class Member implements Serializable {
    private int id;
    private String name;
    private String relation;
    private String title;
    private String address;
    private int number;
    private String image;

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", relation='" + relation + '\'' +
                ", title='" + title + '\'' +
                ", address='" + address + '\'' +
                ", number=" + number +
                ", image='" + image + '\'' +
                '}';
    }

    public Member(int id, String name, String relation, String title, String address, int number) {
        this.id = id;
        this.name = name;
        this.relation = relation;
        this.title = title;
        this.address = address;
        this.number = number;


    }

    public void setId(int id) {
        this.id = id;
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
