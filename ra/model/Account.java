package com.ra.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "Account")
public class Account {
    @Id
    @Column(name = "Acc_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accId;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String passWord;

    @Column(name = "created")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date created;

    @Column(name = "permission")
    private boolean permission;

    @Column(name = "acc_Status")
    private boolean status;

    @OneToMany(mappedBy = "account")
    private Set<BillModel> billModels;

    public Account() {
    }

    public Account(int accId, String email, String passWord, Date created, boolean permission, boolean status, Set<BillModel> billModels) {
        this.accId = accId;
        this.email = email;
        this.passWord = passWord;
        this.created = created;
        this.permission = permission;
        this.status = status;
        this.billModels = billModels;
    }

    public int getAccId() {
        return accId;
    }

    public void setAccId(int accId) {
        this.accId = accId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public boolean isPermission() {
        return permission;
    }

    public void setPermission(boolean permission) {
        this.permission = permission;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Set<BillModel> getBillModels() {
        return billModels;
    }

    public void setBillModels(Set<BillModel> billModels) {
        this.billModels = billModels;
    }
}
