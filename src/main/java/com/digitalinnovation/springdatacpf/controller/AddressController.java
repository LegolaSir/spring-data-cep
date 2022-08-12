package com.digitalinnovation.springdatacpf.controller;

import com.digitalinnovation.springdatacpf.entity.Address;
import com.digitalinnovation.springdatacpf.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService service;

    @GetMapping("/list")
    public ResponseEntity<List<Address>> list(){
        return ResponseEntity.ok().body(service.list());
    }
}
