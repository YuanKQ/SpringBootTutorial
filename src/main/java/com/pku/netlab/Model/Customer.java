package com.pku.netlab.Model;

import java.io.Serializable;

/**
 * @Auther: yuan
 * @Date: 18-6-19 22:56
 * @Description:
 */
public class Customer implements Serializable {
    private static final long serialVersionUID = -6875697006313118650L;

    private long id;
    private String firstName;
    private String lastName;

    protected Customer() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Customer(long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format("Customer[id=%d, firstName='%s', lastName='%s']", id, firstName, lastName);
    }
}
