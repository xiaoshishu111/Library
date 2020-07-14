package com.domain;

import com.enums.ReaderAuthortyEnum;

public class Reader {
    private String readerId;
    private String readerName;
    private String readerAccount;
    private String readerPassword;
    private int readerAuthorty= ReaderAuthortyEnum.AVERAGE_USER.getCode();

    @Override
    public String toString() {
        return "Reader{" +
                "readerId='" + readerId + '\'' +
                ", readerName='" + readerName + '\'' +
                ", readerAccount='" + readerAccount + '\'' +
                ", readerPassword='" + readerPassword + '\'' +
                ", readerAuthorty='" + readerAuthorty + '\'' +
                '}';
    }

    public String getReaderAccount() {
        return readerAccount;
    }

    public void setReaderAccount(String readerAccount) {
        this.readerAccount = readerAccount;
    }

    public String getReaderPassword() {
        return readerPassword;
    }

    public void setReaderPassword(String readerPassword) {
        this.readerPassword = readerPassword;
    }

    public int getReaderAuthorty() {
        return readerAuthorty;
    }

    public void setReaderAuthorty(int readerAuthorty) {
        this.readerAuthorty = readerAuthorty;
    }

    public Reader(String readerId, String readerName) {
        this.readerId = readerId;
        this.readerName = readerName;
    }

    public Reader() {

    }

    public String getReaderId() {
        return readerId;
    }

    public void setReaderId(String readerId) {
        this.readerId = readerId;
    }

    public String getReaderName() {
        return readerName;
    }

    public void setReaderName(String readerName) {
        this.readerName = readerName;
    }



    public Reader(String readerId, String readerName, String readerAccount, String readerPassword, int readerAuthorty) {
        this.readerId = readerId;
        this.readerName = readerName;
        this.readerAccount = readerAccount;
        this.readerPassword = readerPassword;
        this.readerAuthorty = readerAuthorty;
    }
}
