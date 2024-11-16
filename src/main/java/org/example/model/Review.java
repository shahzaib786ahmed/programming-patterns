package org.example.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class Review {
    private String reviewId;
    private String email;
    private String title;
    private String body;

    public Review(String email, String title, String body) {
        this.reviewId = UUID.randomUUID().toString();
        setEmail(email);
        setTitle(title);
        setBody(body);
    }

    /**
     * Sets the email to be used for the review
     * @param email to be used for the review and checks if it contains @
     */
    public void setEmail(String email) {
        if (email != null && email.contains("@")) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Invalid email: must contain '@'.");
        }
    }

    /**
     * Sets the main title of the review
     * @param title of the review
     */
    public void setTitle(String title) {
        if (title != null && !title.trim().isEmpty()) {
            this.title = title;
        } else {
            throw new IllegalArgumentException("Title cannot be null or empty.");
        }
    }

    /**
     * Sets the body description of the review
     * @param body of the review with description
     */
    public void setBody(String body) {
        if (body != null && !body.trim().isEmpty()) {
            this.body = body;
        } else {
            throw new IllegalArgumentException("Body cannot be null or empty.");
        }
    }

    /**
     * Displays the details of the review
     * @return the details of the review
     */
    @Override
    public String toString() {
        return "\n------------------------------------\n" +
                "             Review             \n" +
                "------------------------------------\n" +
                "Email   : " + email + "\n" +
                "Title   : " + title + "\n" +
                "------------------------------------\n" +
                "Description  :\n" + body + "\n" +
                "------------------------------------";
    }
}
