package com.enums;

public enum BookStatusEnum {

    UNBORROWED(0,"图书没有被借出去"),BORROWED(1,"图书已经被借出去了");

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    private int code;
     private String msg;

    BookStatusEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
