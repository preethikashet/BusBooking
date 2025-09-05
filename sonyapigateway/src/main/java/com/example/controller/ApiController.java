package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/kpis")

public class ApiController {
    @Autowired
    public EurekaOne eurekaOne;
    @Autowired
    public EurekaTwo eurekaTwo;

    @GetMapping("/testeurekaclient")
    public String testeurekaclient() {
        return eurekaOne.testeurekaclient();
    }

    @GetMapping("/testeurekaclienttwo")
    public String testeurekaclientTwo() {
        return eurekaTwo.testeurekaclient();
    }

}

@FeignClient(name = "http://EurekaClientOne/c1")
interface EurekaOne{
    @GetMapping("/testeurekaclient")
    public String testeurekaclient() ;
}

@FeignClient(name = "http://EurekaClientTwo/c2")
interface EurekaTwo{
    @GetMapping("/testeurekaclienttwo")
    public String testeurekaclient();

}