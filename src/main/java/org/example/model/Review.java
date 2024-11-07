package org.example.model;

import java.util.UUID;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email != null && email.contains("@")) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Invalid email: must contain '@'.");
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title != null && !title.trim().isEmpty()) {
            this.title = title;
        } else {
            throw new IllegalArgumentException("Title cannot be null or empty.");
        }
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        if (body != null && !body.trim().isEmpty()) {
            this.body = body;
        } else {
            throw new IllegalArgumentException("Body cannot be null or empty.");
        }
    }

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

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
