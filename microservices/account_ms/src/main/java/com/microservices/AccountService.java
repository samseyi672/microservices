package com.activedge;


import com.activedge.model.Balance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class AccountService {

    @Autowired
    AccountReposisitory accountReposisitory ;

    public Optional<Balance> findById(Long  id){
        return accountReposisitory.findById(id) ;
    }
}
