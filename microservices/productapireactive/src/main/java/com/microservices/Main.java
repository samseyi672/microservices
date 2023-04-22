package com.activedge;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}