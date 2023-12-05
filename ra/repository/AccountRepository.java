package com.ra.repository;

import com.ra.model.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;


public interface AccountRepository extends JpaRepository<Account, Integer> {
    @Query("select a from Account a where a.email like %?1%")
    Page<Account> findByEmail(String email, Pageable pageable);
    int countByEmailContains(String email);

    List<Account> findAll();
    List<Account> findAllByStatusIsTrue();

    @Query("SELECT COUNT(ac) FROM Account ac WHERE ac.status = true")
    int statisticalAccountActive();

    @Query("select count(ac) from Account ac where ac.status = false ")
    int statisticalAccountInActive();
}