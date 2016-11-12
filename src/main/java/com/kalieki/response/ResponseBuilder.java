package com.kalieki.response;

import java.util.ArrayList;

import static java.text.MessageFormat.format;

/**
 * Created by kalieki on 10/8/16.
 */
public class ResponseBuilder {

    private Response resp;

    public ResponseBuilder() {
        resp = new Response();
    }

    public Response build(){
        return resp;
    }

    public ResponseBuilder withEntity(Object o){
        resp.setEntity(o);
        return this;
    }

    public ResponseBuilder withSuccessToast(String title, String msg, String... params){
        return withToast(title, format(msg, params), ToastType.Success);
    }

    public ResponseBuilder withErrorToast(String title, String msg, String... params){
        return withToast(title, format(msg,params), ToastType.Error);
    }


    public ResponseBuilder withToast(String title, String msg, ToastType type){

        initToasts();
        Toast toast = new Toast();
        toast.setMessage(msg);
        toast.setTitle(title);
        toast.setType(ToastType.Success);
        resp.getMessages().add(toast);
        return this;
    }



    private void initToasts() {
        if(resp.getMessages() == null){
            resp.setMessages(new ArrayList<>());
        }
    }
}
