package com.ra.serviceImp;

import com.ra.model.Account;
import com.ra.repository.AccountRepository;
import com.ra.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountServiceImp implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<Account> displayDataAcc(String email, int page, int size, String direction, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, direction.equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy);
        List<Account> lissAccounts = accountRepository.findByEmail(email, pageable).getContent();
        return lissAccounts;
    }

    @Override
    public List<Integer> getListPage(String email, int size) {
        int countAcc = accountRepository.countByEmailContains(email);
        List<Integer> listPage = new ArrayList<>();
        for (int i = 0; i < (int) Math.ceil((double) countAcc / (double) size); i++) {
            listPage.add(i + 1);
        }
        return listPage;
    }

    @Override
    public Account findById(int accId) {
        try {
            Account accById = accountRepository.findById(accId).get();
            return accById;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Account> getAllAccount() {
        return null;
    }

    @Override
    public boolean save(Account account) {
        try {
            accountRepository.save(account);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Account account) {
        try {
            accountRepository.save(account);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int accId) {
        try {
            accountRepository.deleteById(accId);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public int accStatusTrue() {
        return 0;
    }

    @Override
    public int accStatusFalse() {
        return 0;
    }


}
