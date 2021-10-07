package com.mycompany.adpfp.datas.venue;

import java.lang.reflect.Field;
import java.time.LocalDate;

public class Venue {
    private String id;
    private String name;
    private String location;
    private double cost;
    private int maxNumGuest;
    private LocalDate date;
    private String categoryId;

    public Venue() {
    }

    public Venue(String id, String name, String location, double cost, int maxNumGuest, LocalDate date, String categoryId) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.cost = cost;
        this.maxNumGuest = maxNumGuest;
        this.date = date;
        this.categoryId = categoryId;
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

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getMaxNumGuest() {
        return maxNumGuest;
    }

    public void setMaxNumGuest(int maxNumGuest) {
        this.maxNumGuest = maxNumGuest;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public boolean isNull() {
        Field fields[] = this.getClass().getDeclaredFields();
        for (Field f : fields) {
            try {
                Object value = f.get(this);
                if (value == null) {
                    return true;
                }
            }
            catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }


        }
        return true;

    }

    @Override
    public String toString() {
        return "Venue{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", cost=" + cost +
                ", maxNumGuest=" + maxNumGuest +
                ", date=" + date +
                ", categoryId='" + categoryId + '\'' +
                '}';
    }
}
