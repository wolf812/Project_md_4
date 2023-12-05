package com.ra.serviceImp;

import com.ra.model.Account;
import com.ra.model.BillDetailModel;
import com.ra.repository.BillDetailRepository;
import com.ra.service.BillDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BillDetailServiceImp implements BillDetailService {
    @Autowired
    private BillDetailRepository billDetailRepository;
    @Override
    public List<BillDetailModel> displayData(String billId, int page, int size, String direction, String sortBy) {
        Pageable pageable = PageRequest.of(page,size,direction.equals("ASC")? Sort.Direction.ASC: Sort.Direction.DESC,sortBy);
        List<BillDetailModel> billDetailList = billDetailRepository.findByBillDetailId(billId,pageable).stream().distinct().collect(Collectors.toList());
        return billDetailList;
    }

    @Override
    public List<Integer> getListPage(String billId, int size) {
        int countBill= billDetailRepository.countByBill_BillId(billId);
        List<Integer> listPage = new ArrayList<>();
        for (int i = 0; i < (int)Math.ceil((double) countBill/(double) size); i++) {
            listPage.add(i+1);
        }
        return listPage;
    }

    @Override
    public List<BillDetailModel> findAll() {
        return billDetailRepository.findAll();
    }

    @Override
    public List<BillDetailModel> findBillByBillDetail(String billId) {
        return billDetailRepository.findBillDetailModelByBill_BillId(billId);
    }

    @Override
    public BillDetailModel findByDetailId(int billDetailId) {
        return billDetailRepository.findById(billDetailId).get();
    }

    @Override
    public boolean save0rUpdate(BillDetailModel billDetail) {
        try {
            billDetailRepository.save(billDetail);
            return true;
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int billDetailId) {
        try {
            billDetailRepository.delete(findByDetailId(billDetailId));
            return true;
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public Double sumInDay() {
        return billDetailRepository.sumInDay();
    }

    @Override
    public double sumInMonth() {
        return billDetailRepository.sumInMonth();
    }

    @Override
    public double sumInYear() {
        return billDetailRepository.sumInYear();
    }
}

