package com.example.etrain;

public class Parcel {
    String serialNumber;
    String from;
    String to;
    String Weight;
    String date;
    String description;

    public Parcel() {
    }

    public Parcel(String serialNumber, String from, String to, String weight, String date, String description) {
        this.serialNumber = serialNumber;
        this.from = from;
        this.to = to;
        Weight = weight;
        this.date = date;
        this.description = description;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String parcelCode) {
        this.serialNumber = parcelCode;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getWeight() {
        return Weight;
    }

    public void setWeight(String weight) {
        this.Weight = weight;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
