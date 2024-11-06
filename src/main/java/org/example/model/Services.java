package org.example.model;

public abstract class Services {
    private String serviceName;
    private double price;
    private String description;

    public Services(String serviceName, double price, String description) {
        this.serviceName = serviceName;
        this.price = price;
        this.description = description;
    }

    public abstract void displayDetails();

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
