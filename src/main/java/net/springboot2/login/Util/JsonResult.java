package net.springboot2.login.Util;
import java.io.Serializable;

public class JsonResult<E> implements Serializable{
    private Integer state;
    private String message;
    private E data;
    private String token;

    public JsonResult() {
        super();
    }

    public JsonResult(Integer state) {
        super();
        this.state = state;
    }

    public JsonResult(Integer state, String message) {
        super();
        this.state = state;
        this.message = message;
    }

    public JsonResult(Throwable e) {
        super();
        this.message = e.getMessage();
    }

    public JsonResult(Integer state, E data, String message) {
        super();
        this.state = state;
        this.data = data;
        this.message = message;
    }

    public JsonResult(Integer state, E data, String message, String token) {
        super();
        this.state = state;
        this.data = data;
        this.message = message;
        this.token = token;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }
}
