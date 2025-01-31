package com.letsplay.bean;

public class ResponseBean {
    private int choice;
    private String message;
    public ResponseBean(int choice, String message) {
        this.choice = choice;
        this.message = message;
    }
    public void setChoice(int choice) {
        this.choice = choice;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public int getChoice() {
        return choice;
    }
    public String getMessage() {
        return message;
    }
}
