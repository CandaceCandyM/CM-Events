package com.kenzie.appserver.service;

public class Event {
    private String id;
    private String name;
    private String location;
    private String date;
    private String time;
    private String duration;
    private String price;
    private String category;
    private int rating;

    public Event() {
    }

    public Event(String id, String name, String location, String date, String time, String duration, String price, String category, int rating) {
        if (id == null) {
            throw new IllegalArgumentException("Event id cannot be null");
        }
        if (name == null) {
            throw new IllegalArgumentException("Event name cannot be null");
        }
        if (location == null) {
            throw new IllegalArgumentException("Event location cannot be null");
        }
        if (date == null) {
            throw new IllegalArgumentException("Event date cannot be null");
        }
        if (time == null) {
            throw new IllegalArgumentException("Event time cannot be null");
        }
        if (duration == null) {
            throw new IllegalArgumentException("Event duration cannot be null");
        }
        if (price == null) {
            throw new IllegalArgumentException("Event price cannot be null");
        }
        if (category == null) {
            throw new IllegalArgumentException("Event category cannot be null");
        }
        this.id = id;
        this.name = name;
        this.location = location;
        this.date = date;
        this.time = time;
        this.duration = duration;
        this.price = price;
        this.category = category;
        this.rating = rating;
    }

    public Event(String id, String name, String location, String date, String time, String duration, String price, String category, String rating) {
    }

    public Event(String name) {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
    @Override
    public String toString() {
        return "Event{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", duration='" + duration + '\'' +
                ", price='" + price + '\'' +
                ", category='" + category + '\'' +
                ", rating='" + rating + '\'' +
                '}';
    }
}