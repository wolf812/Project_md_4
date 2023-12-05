package com.ra.serviceImp;
import com.ra.model.Account;
import com.ra.repository.AccountRepository;
import com.ra.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginServiceImp implements LoginService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public boolean login(String email, String passWord) {
        List<Account> listAccount = accountRepository.findAllByStatusIsTrue();
        for (int i = 0; i < listAccount.size(); i++) {
            if (listAccount.get(i).getEmail().equals(email) &&
                    listAccount.get(i).getPassWord().equals(passWord)
            ) {
                return true;
            }
        }
        return false;
    }
}
