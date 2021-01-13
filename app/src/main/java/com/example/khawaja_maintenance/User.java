package com.example.khawaja_maintenance;

public class User
{
    String name;
    String city;
    String date;
    String time;
    String address;
    String mobile;
    String instruction;
    String more_services;
    int order_no;

    public int getOrder_no() {
        return order_no;
    }

    public void setOrder_no(int order_no) {
        this.order_no = order_no;
    }

    public User()
    {

    }

    public String getName()
    {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getAddress() {
        return address;
    }

    public String getMobile() {
        return mobile;
    }

    public String getInstruction() {
        return instruction;
    }

    public String getMore_services() {
        return more_services;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public void setMore_services(String more_services) {
        this.more_services = more_services;
    }

}
