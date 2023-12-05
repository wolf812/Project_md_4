package com.ra.service;

import com.ra.model.Account;

import java.util.List;

public interface AccountService {
    List<Account> displayDataAcc(String email, int page, int size, String direction, String sortBy);


    List<Integer> getListPage(String email, int size);

    Account findById(int accId);
    List<Account> getAllAccount();

    boolean save(Account account);

    boolean update(Account account);

    boolean delete(int accId);
    int accStatusTrue();
    int accStatusFalse();

}
