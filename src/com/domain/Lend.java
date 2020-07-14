package com.domain;

import java.sql.Timestamp;
import java.util.Date;

public class Lend {
    private String lendId;
    private String readerId;
    private String bookId;
    private Timestamp beginTime=null;
    private Timestamp endTime=null;

    public String getLendId() {
        return lendId;
    }

    public void setLendId(String lendId) {
        this.lendId = lendId;
    }

    public String getReaderId() {
        return readerId;
    }

    public void setReaderId(String readerId) {
        this.readerId = readerId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public Timestamp getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Timestamp beginTime) {
        this.beginTime = beginTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Lend{" +
                "lendId='" + lendId + '\'' +
                ", readerId='" + readerId + '\'' +
                ", bookId='" + bookId + '\'' +
                ", beginTime=" + beginTime +
                ", endTime=" + endTime +
                '}';
    }

    public Lend(String lendId, String readerId, String bookId, Timestamp beginTime, Timestamp endTime) {
        this.lendId = lendId;
        this.readerId = readerId;
        this.bookId = bookId;
        this.beginTime = beginTime;
        this.endTime = endTime;
    }

    public Lend() {
    }
}
