package com.ra.controller;

import com.ra.model.*;
import com.ra.service.AccountService;
import com.ra.service.BillDetailService;
import com.ra.service.BillService;
import com.ra.model.BillModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("billController")
public class BillController {
    @Autowired
    private BillService billService;
    @Autowired
    private BillDetailService billDetailService;
    @Autowired
    private AccountService accountService;
    private static final int SIZE = 1;
    private static String billIdDefault = "";
    private static String directionDefault = "ASC";
    private static String sortByDefaultBill = "billId";
    private static String sortByDefaultBillDetail = "billDetailId";
    private static int pageDefault = 1;

    @GetMapping("findBill")
    public ModelAndView displayProduct(Optional<String> billId, Optional<Integer> page,
                                       Optional<String> direction, Optional<String> sortBy) {
        ModelAndView mav = new ModelAndView("bill-test");
        billIdDefault = billId.orElse(billIdDefault);
        directionDefault = direction.orElse(directionDefault);
        sortByDefaultBill = sortBy.orElse(sortByDefaultBill);
        sortByDefaultBillDetail = sortBy.orElse(sortByDefaultBillDetail);
        pageDefault = page.orElse(pageDefault);
        List<BillModel> billlList = billService.displayData(billIdDefault,
                pageDefault - 1, SIZE, directionDefault, sortByDefaultBill);
        billlList.stream().forEach(billModel -> billModel.setListBillDetail(billDetailService.findBillByBillDetail(billModel.getBillId())));
        List<Integer> listPage = billService.getListPage(billIdDefault, SIZE);
        mav.addObject("listBill", billlList);
        mav.addObject("listPage", listPage);
        mav.addObject("billIdSearch", billIdDefault);
        mav.addObject("direction", directionDefault);
        mav.addObject("sortBy", sortByDefaultBill);
        return mav;
    }

    @GetMapping("initUpdate")
    public String initUpdateBill(ModelMap modelMap, String billId) {
        BillModel editBill = billService.findById(billId);
        List<BillDetailModel> billDetailModelList = billDetailService.findBillByBillDetail(billId);
        BillDetailModel editBillDetail = new BillDetailModel();
        modelMap.addAttribute("editBill", editBill);
        modelMap.addAttribute("editBillDetail", editBillDetail);
        modelMap.addAttribute("billDetailList", billDetailModelList);
        return "bill-update-test";
    }

    @PostMapping(value = "/create")
    public String saveBill(BillModel billModel) {
        boolean result = billService.save(billModel);
        if (result) {
            return "redirect:findBill";
        } else {
            return "error";
        }
    }

    @PostMapping("update")
    public String update(BillModel editBill) {
        boolean result = billService.update(editBill);
        if (result) {
            return "redirect:findBill";
        } else {
            return "error";
        }
    }

    @GetMapping("test")
    public String test() {
        return "test/Bill";
    }
}
