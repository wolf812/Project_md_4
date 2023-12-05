package com.ra.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Product")
public class Product {
    @Id
    @Column(name = "product_id",length = 5)
    private String productId;
    @Column(name = "product_name",length = 150,nullable = false,unique = true)
    private String productName;
    @Column(name = "price")
    private float price;
    @Column(name = "title")
    private String title;
    @Column(name = "product_description")
    private String description;
    @Column(name = "product_unit")
    private String unit;
    @Column(name = "product_status")
    private boolean status;
    @ManyToOne
    @JoinColumn(name = "catalog_id",referencedColumnName = "catalog_id")
    private Categories catalog;

    @Column(name = "product_image")
    private String image;

    @OneToMany(mappedBy = "product")
    private List<Images> listImages;

    @OneToMany(mappedBy = "product")
    private List<BillDetailModel>billDetailModels;

    public Product() {
    }

    public Product(String productId, String productName, float price, String title, String description, String unit, boolean status, Categories catalog, String image, List<Images> listImages, List<BillDetailModel> billDetailModels) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.title = title;
        this.description = description;
        this.unit = unit;
        this.status = status;
        this.catalog = catalog;
        this.image = image;
        this.listImages = listImages;
        this.billDetailModels = billDetailModels;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Categories getCatalog() {
        return catalog;
    }

    public void setCatalog(Categories catalog) {
        this.catalog = catalog;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Images> getListImages() {
        return listImages;
    }

    public void setListImages(List<Images> listImages) {
        this.listImages = listImages;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public List<BillDetailModel> getBillDetailModels() {
        return billDetailModels;
    }

    public void setBillDetailModels(List<BillDetailModel> billDetailModels) {
        this.billDetailModels = billDetailModels;
    }
}
