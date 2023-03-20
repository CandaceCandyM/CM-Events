package com.kenzie.appserver.service;

public class Venue {
    private String name;
    private String address;
    private String phone;
    private String website;
    private String email;
    private String description;
    private String id;

    public Venue(String name, String address, String phone, String website, String email, String description, String id) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.website = website;
        this.email = email;
        this.description = description;
        this.id = id;
    }

    public Venue(String name, String address) {
        this(name, address, null, null, null, null, null);
    }

    public Venue() {
        this(null, null, null, null, null, null, null);
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getWebsite() {
        return website;
    }

    public String getEmail() {
        return email;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}