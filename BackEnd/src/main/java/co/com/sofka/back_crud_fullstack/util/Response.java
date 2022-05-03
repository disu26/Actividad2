package co.com.sofka.back_crud_fullstack.util;

public class Response {
    public Boolean error = false;
    public String message = "";
    public Object data;

    public void restart() {
        error = false;
        message = "";
        data = null;
    }
}
