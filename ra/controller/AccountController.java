package com.ra.controller;

import com.ra.model.Account;
import com.ra.service.AccountService;
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
@RequestMapping("/accountController")
public class AccountController {
    @Autowired
    private AccountService accountService;
    private static final int SIZE = 3;
    private static String emailDefault = "";
    private static String directionDefault = "ASC";
    private static String sortByDefault = "accId";
    private static int pageDefault = 1;

    @GetMapping("/findAccount")
    public ModelAndView displayAccount(Optional<String> email, Optional<Integer> page, Optional<String> direction, Optional<String> sortBy) {
        ModelAndView mav = new ModelAndView("account-test");
        emailDefault = email.orElse(emailDefault);
        directionDefault = direction.orElse(directionDefault);
        sortByDefault = sortBy.orElse(sortByDefault);
        pageDefault = page.orElse(pageDefault);
        List<Account> accountList = accountService.displayDataAcc(emailDefault, pageDefault - 1, SIZE, directionDefault, sortByDefault);
        List<Integer> listPages = accountService.getListPage(emailDefault, SIZE);
        mav.addObject("accountList", accountList);
        mav.addObject("listPages", listPages);
        mav.addObject("catalogName", emailDefault);
        mav.addObject("sortBy", sortByDefault);
        mav.addObject("direction", directionDefault);
        return mav;
    }

    @GetMapping(value = "/initUpdate")
    public String initAccUpdate(ModelMap modelMap, int accId) {
        Account accountEdit = accountService.findById(accId);
        modelMap.addAttribute("accountEdit", accountEdit);
        return "accountUpdate";
    }

    @GetMapping(value = "/intCreate")
    public String createAccount(ModelMap modelMap) {
        Account account = new Account();
        modelMap.addAttribute("account",account);
        return "newAccount";
    }

    @PostMapping(value = "/create")
    public String saveAcc(Account account){
        boolean result = accountService.save(account);
        if (result) {
            return "redirect:findAccount";
        }else {
            return "error";
        }
    }

    @PostMapping(value = "/update")
    public String updateAcc(Account account) {
        boolean result = accountService.update(account);
        if (result) {
            return "redirect:findAccount";
        } else {
            return "error";
        }
    }

    @GetMapping("/delete")
    public String deleteAccount(int accId) {
        boolean result = accountService.delete(accId);
        if (result) {
            return "redirect:findAccount";
        } else {
            return "error";
        }
    }

}
