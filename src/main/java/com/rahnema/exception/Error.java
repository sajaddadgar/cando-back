package com.rahnema.exception;

public class Error extends Exception {

    private String detail;
    private int code;


    public Error(String detail, int code) {
        super();
        this.code = code;
        this.detail = detail;
    }

    public Error() {
        super();
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
