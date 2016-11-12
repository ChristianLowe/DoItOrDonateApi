package com.kalieki.response;

/**
 * Created by kalieki on 10/8/16.
 */
public class Toast {
    private ToastType type;
    private String message;
    private String title;


    public ToastType getType() {
        return type;
    }

    public void setType(ToastType type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
