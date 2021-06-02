package com.wsine.west.exp7;

/**
 * Created by West on 2015/11/25.
 */
public class Contact {
    private  int id;
    private String no;
    private String sex;
    private String name;
    private String phoneNumber;

    public Contact(String no, String name, String sex, String phoneNumber) {
        this.no = no;
        this.sex = sex;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public Contact(int id, String no, String name, String sex, String phoneNumber) {
        this.id = id;
        this.no = no;
        this.sex = sex;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public Contact() {}
    public Contact(String _name, String _phoneNumber) {
        this.name = _name;
        this.phoneNumber = _phoneNumber;
    }
    public Contact(String _no, String _name, String _phoneNumber) {
        this.no = _no;
        this.name = _name;
        this.phoneNumber = _phoneNumber;
    }

    public String getNo() { return no; }
    public void setNo(String _no) { this.no = _no; }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getName() { return name; }
    public void setName(String _name) { this.name = _name; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String _phoneNumber) { this.phoneNumber = _phoneNumber; }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
