package com.ra.service;

import com.ra.model.Account;
import com.ra.model.BillDetailModel;

import java.util.List;
import java.util.Set;

public interface BillDetailService {
    List<BillDetailModel> displayData(String billId, int page, int size, String direction, String sortBy);
    List<Integer>getListPage(String billId, int size);
    List<BillDetailModel> findAll();
    List<BillDetailModel>findBillByBillDetail(String billId);
    BillDetailModel findByDetailId(int billDetailId);
    boolean save0rUpdate(BillDetailModel billDetail);
    boolean delete(int billDetailId);
    Double sumInDay();
    double sumInMonth();
    double sumInYear();
}
