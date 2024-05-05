package com.j2ee.shoestoreweb.entity;

import java.util.Date;

public class Review {
    private int accountID;
    private int productID;
    private String contentReview;
    private Date dateReview;

    public Review() {
    }

    public Review(int accountID, int productID, String contentReview, Date dateReview) {
        this.accountID = accountID;
        this.productID = productID;
        this.contentReview = contentReview;
        this.dateReview = dateReview;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getContentReview() {
        return contentReview;
    }

    public void setContentReview(String contentReview) {
        this.contentReview = contentReview;
    }

    public Date getDateReview() {
        return dateReview;
    }

    public void setDateReview(Date dateReview) {
        this.dateReview = dateReview;
    }
}