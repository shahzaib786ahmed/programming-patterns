package org.example.model;

public abstract class Services {
    private String serviceName;
    private String description;

    public Services(String serviceName,String description) {
        this.serviceName = serviceName;
        this.description = description;
    }

    public abstract void displayDetails();

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
