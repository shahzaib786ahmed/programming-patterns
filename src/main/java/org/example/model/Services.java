package org.example.model;

import lombok.*;

@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
public abstract class Services {
    private String serviceName;
    private String description;

    public Services(String serviceName, String description) {
        this.serviceName = serviceName;
        this.description = description;
    }

    /**
     * Display the details of the service
     */
    public abstract void displayDetails();
}
