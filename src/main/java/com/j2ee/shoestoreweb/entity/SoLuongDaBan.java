package com.j2ee.shoestoreweb.entity;

public class SoLuongDaBan {
    private int productID;
    private int soLuongDaBan;

    public SoLuongDaBan() {
    }

    public SoLuongDaBan(int productID, int soLuongDaBan) {
        this.productID = productID;
        this.soLuongDaBan = soLuongDaBan;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getSoLuongDaBan() {
        return soLuongDaBan;
    }

    public void setSoLuongDaBan(int soLuongDaBan) {
        this.soLuongDaBan = soLuongDaBan;
    }
}