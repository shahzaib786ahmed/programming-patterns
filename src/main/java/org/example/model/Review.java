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
     *
     * @param email
     */
    public void setEmail(String email) {
        if (email != null && email.contains("@")) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Invalid email: must contain '@'.");
        }
    }

    /**
     *
     * @param title
     */
    public void setTitle(String title) {
        if (title != null && !title.trim().isEmpty()) {
            this.title = title;
        } else {
            throw new IllegalArgumentException("Title cannot be null or empty.");
        }
    }

    /**
     *
     * @param body
     */
    public void setBody(String body) {
        if (body != null && !body.trim().isEmpty()) {
            this.body = body;
        } else {
            throw new IllegalArgumentException("Body cannot be null or empty.");
        }
    }

    /**
     *
     * @return
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
