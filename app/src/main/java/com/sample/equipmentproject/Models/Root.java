package com.sample.equipmentproject.Models;

public class Root {
    public int id;
    public String vin;
    public int year;
    public String make;
    public double value;
    public double length;

    public int getId() {
        return id;
    }

    public String getVin() {
        return vin;
    }

    public int getYear() {
        return year;
    }

    public String getMake() {
        return make;
    }

    public double getValue() {
        return value;
    }

    public double getLength() {
        return length;
    }

    @Override
    public String toString() {
        return "Root{" +
                "id=" + id +
                ", vin='" + vin + '\'' +
                ", year=" + year +
                ", make='" + make + '\'' +
                ", value=" + value +
                ", length=" + length +
                '}';
    }
}

