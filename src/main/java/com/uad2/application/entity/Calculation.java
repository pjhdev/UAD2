package com.uad2.application.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Calculation {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int seq;

    @Column(name = "matching_seq", nullable = false)
    private int matchingSeq;

    @Column(nullable = false)
    private int price;

    @Column
    private String content;

    @Column(nullable = false)
    private int kind;

    @Column(name = "calculation_date")
    private Date calculationDate;

    @Column(name = "attend_cnt", nullable = false)
    private int attendCnt;


    @Column(nullable = false, name = "create_at")
    private Date createdAt;

    @Column(nullable = false, name = "update_at")
    private Date updatedAt;


    @PrePersist
    void createdAt() {
        this.createdAt = this.updatedAt = new Date();
    }

    @PreUpdate
    void updatedAt() {
        this.updatedAt = new Date();
    }


    public int getSeq() {
        return seq;
    }

    public int getMatchingSeq() {
        return matchingSeq;
    }

    public int getPrice() {
        return price;
    }

    public String getContent() {
        return content;
    }

    public int getKind() {
        return kind;
    }

    public Date getCalculationDate() {
        return calculationDate;
    }

    public int getAttendCnt() {
        return attendCnt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }


    @Override
    public String toString() {
        return "Calculation{" +
                "seq=" + seq +
                ", matchingSeq=" + matchingSeq +
                ", price=" + price +
                ", content='" + content + '\'' +
                ", kind=" + kind +
                ", calculationDate=" + calculationDate +
                ", attendCnt=" + attendCnt +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}