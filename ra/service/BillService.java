package com.ra.service;

import com.ra.model.Account;
import com.ra.model.BillModel;

import java.util.List;

public interface BillService {
    List<BillModel> displayData(String billId, int page, int size, String direction, String sortBy);
    List<Integer>getListPage(String billId, int size);
    List<BillModel> findAll();
    BillModel findById(String billd);
    boolean save(BillModel billModel);

    boolean update(BillModel editBill);

    boolean delete(String billd);
    int billStatus(int status);
    int billAll ();
}
