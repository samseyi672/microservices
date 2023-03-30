package com.activedge.controller;


import com.activedge.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/v1/account")
@Slf4j
public class AccountController {

    @Autowired
    AccountService accountService ;

    @Value("${api.version}")
    private String apiVersion ;

    @GetMapping("/accountBalance/{id}")
    public ResponseEntity<?> getAccountBalance(@PathVariable Integer id) {
        return accountService.findById(Long.valueOf(id))
                .map(accountBalance -> {
                    try {
                        return ResponseEntity
                                .ok()
                                .eTag(apiVersion)
                                .location(new URI("/accountBalance/" + accountBalance.getId()))
                                .body(accountBalance);
                    } catch (URISyntaxException e ) {
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                    }
                })
                .orElse(ResponseEntity.notFound().build());
    }
}































































































































































