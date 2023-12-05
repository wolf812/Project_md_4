package com.ra.controller;

import com.ra.service.AccountService;
import com.ra.service.BillDetailService;
import com.ra.service.BillService;
import com.ra.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/dashboard")
public class DashBoardController {
    @Autowired
    AccountService accountService;
    @Autowired
    BillService billService;
    @Autowired
    ProductService productService;
    @Autowired
    BillDetailService billDetailService;
    @GetMapping("data")
    public ModelAndView displayData(){
        ModelAndView mav = new ModelAndView("dashBoard");
        int accStatusTrue = accountService.accStatusTrue();
        int accStatusFalse = accountService.accStatusFalse();
        int accAll = accountService.getAllAccount().size();


        int sumProduct = productService.findAll().size();
        int productStatusFalse = productService.productStatusFalse();
        int productStatusTrue = productService.productStatusTrue();

        int billCancel = billService.billStatus(2);
        int billDone = billService.billStatus(4);
        int billWiting = billService.billStatus(1);
        int billApprove = billService.billStatus(3);
        int sumBill = billService.billAll();

        Double revenueInDay = billDetailService.sumInDay();
//        double revenueInMonth = billDetailService.sumInMonth();
//        double revenueInYear = billDetailService.sumInYear();

        mav.addObject("accStatusTrue",accStatusTrue);
        mav.addObject("accStatusFalse",accStatusFalse);
        mav.addObject("accAll",accAll);

        mav.addObject("sumProduct",sumProduct);
        mav.addObject("productStatusFalse", productStatusFalse);
        mav.addObject("productStatusTrue", productStatusTrue);

        mav.addObject("billWiting", billWiting); // 1.chờ.2huy.3 dng giao. 4.ok
        mav.addObject("billCancel", billCancel);
        mav.addObject("billApprove", billApprove);
        mav.addObject("billDone", billDone);
        mav.addObject("sumBill", sumBill);

        mav.addObject("revenueInDay",revenueInDay);
//        mav.addObject("revenueInMonth",revenueInMonth);
//        mav.addObject("revenueInYear",revenueInYear);


        return mav;
    }
}
