package br.com.pitang.themagi;

import java.io.Serializable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class ChatMessage implements Serializable {

    @Retention(RetentionPolicy.SOURCE)
    public @interface TYPE{
        int LEFT = 0;
        int RIGHT = 1;
    }

    private String message;

    @TYPE
    private int type;

    public ChatMessage(String message,@TYPE int type) {
        this.message = message;
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @TYPE
    public int getType() {
        return type;
    }

    public void setType(@TYPE int type) {
        this.type = type;
    }
}
