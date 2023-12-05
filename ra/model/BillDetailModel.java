package com.ra.model;


import javax.persistence.*;

@Entity
@Table(name = "BillDetail")

public class BillDetailModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "billDetail_id")
    private int billDetailId;

    @Column(name = "purchasedPrice")
    private float purchasedPrice;

    @Column(name = "quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "bill_id", referencedColumnName = "bill_id")
    private BillModel bill;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product product;

    public BillDetailModel() {
    }

    public BillDetailModel(int billDetailId, float purchasedPrice, int quantity, BillModel bill, Product product) {
        this.billDetailId = billDetailId;
        this.purchasedPrice = purchasedPrice;
        this.quantity = quantity;
        this.bill = bill;
        this.product = product;
    }

    public int getBillDetailId() {
        return billDetailId;
    }

    public void setBillDetailId(int billDetailId) {
        this.billDetailId = billDetailId;
    }

    public float getPurchasedPrice() {
        return purchasedPrice;
    }

    public void setPurchasedPrice(float purchasedPrice) {
        this.purchasedPrice = purchasedPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BillModel getBill() {
        return bill;
    }

    public void setBill(BillModel bill) {
        this.bill = bill;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
