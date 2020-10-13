package com.example.etrain;


public class Feedback {
    String feedback;
    String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Feedback(String feedback, String email) {
        this.feedback = feedback;
        this.email = email;
    }

    public Feedback() {
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
