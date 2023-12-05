package com.ra.repository;

import com.ra.model.BillDetailModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BillDetailRepository extends JpaRepository<BillDetailModel,Integer> {
    @Query("select bd from BillDetailModel bd inner join BillModel b on bd.bill.billId = b.billId where b.billId like %?1%")
    Page<BillDetailModel> findByBillDetailId(String billId, Pageable pageable );
    @Query("select count (*) from BillDetailModel b where b.bill.billId like %?1%")
    int countByBill_BillId(String billId);
    @Query("select bd from BillDetailModel bd inner join BillModel b on bd.bill.billId = b.billId where b.billId like %?1%")
    List<BillDetailModel>findBillDetailModelByBill_BillId(String billId);
    @Query("select sum(b.purchasedPrice*b.quantity) from BillDetailModel b where b.bill.created = month (current_date)")
    Double sumInDay();
    @Query("select sum(b.purchasedPrice*b.quantity) from BillDetailModel b")
    double sumInMonth();
    @Query("select sum(b.purchasedPrice*b.quantity) from BillDetailModel b ")
    double sumInYear();
}
