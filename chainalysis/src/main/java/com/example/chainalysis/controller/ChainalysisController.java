package com.example.chainalysis.controller;
import com.example.chainalysis.service.ChainalysisService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;


@RestController
@RequestMapping("/")
public class ChainalysisController {

    private ChainalysisService chainalysisService;

    @Autowired
    public ChainalysisController(ChainalysisService service) {
        this.chainalysisService = service;
    }

    @CrossOrigin
    @GetMapping("/prices/coinbase")
    public Map<String, String> getCoinbase() throws IOException, InterruptedException {
        return chainalysisService.getCoinbasePrices();
    }

    @CrossOrigin
    @GetMapping("/prices/binance")
    public Map<String, String> getBinance() throws IOException, InterruptedException {
        return chainalysisService.getBinance();
    }
}
