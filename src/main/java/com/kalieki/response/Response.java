package com.kalieki.response;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kalieki on 9/24/16.
 */
public class Response {

    private List<Toast> messages;
    private Object entity;


    public Response() {
        this.messages = new ArrayList<>();
    }

    public Response(Object entity) {
        this.entity = entity;
        this.messages = new ArrayList<>();
    }


    public Object getEntity() {
        return entity;
    }

    public void setEntity(Object entity) {
        this.entity = entity;
    }

    public List<Toast> getMessages() {
        return messages;
    }

    public void setMessages(List<Toast> messages) {
        this.messages = messages;
    }
}
