package com.sys.util;

import java.util.List;

public class OperationResult <T>{
    private int statuscode;
    private String message;
    private T data;

    public OperationResult()
    {
        statuscode = 0;
        message = "";
        data = null;
    }

    public OperationResult(int statuscode, String message)
    {
        this.statuscode = statuscode;
        this.message = message;
        data = null;
    }

    public OperationResult(int statuscode, String message, T data)
    {
        this.statuscode = statuscode;
        this.message = message;
        this.data = data;
    }

    public int getStatuscode() {
        return statuscode;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

}
