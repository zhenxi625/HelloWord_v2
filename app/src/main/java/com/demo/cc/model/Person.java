package com.demo.cc.model;

import java.io.Serializable;

/**
 * Created by cc on 2016/11/4.
 */

public class Person implements Serializable {

    private static final long serialVersionUID = 5864276445070634002L;

    private Integer id;
    private String name;
    private String pass;
    private String gender;

    public Person() {
    }

    public Person(String name, String pass, String gender) {
        this.name = name;
        this.pass = pass;
        this.gender = gender;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
