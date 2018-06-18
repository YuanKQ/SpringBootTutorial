package com.pku.netlab.Dao;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * @Auther: yuan
 * @Date: 18-6-17 16:57
 * @Description:
 */
public class Person {
    protected int age;
    public String fullName;
    public String lastName;

    @JSONField(name = "DATE OF BRITH", format = "yyyy-mm-dd")  // format just works for Serialize
    public Date dateOfBirth;

    public Person() {

    }

    public Person(int age, String fullName, String lastName, Date dateOfBirth) {
        this.age = age;
        this.fullName = fullName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void getOlder() {
        age ++;
    }

    @Override
    public String toString() {
        return "Person:"+
                "\n\tage: " + age +
                "\n\tfullName: " + fullName +
                "\n\tlastName: " + lastName +
                "\n\tDATE OF BIRTH: " + dateOfBirth;
    }
}
