package com.enums;

public enum ReaderAuthortyEnum {
AVERAGE_USER(0,"普通用户"),ADMIN_USER(1,"管理员用户");
private int code;
private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    ReaderAuthortyEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
