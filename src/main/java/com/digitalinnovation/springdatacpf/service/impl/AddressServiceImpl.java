package com.digitalinnovation.springdatacpf.service.impl;

import com.digitalinnovation.springdatacpf.entity.Address;
import com.digitalinnovation.springdatacpf.repository.AddressRepository;
import com.digitalinnovation.springdatacpf.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository repository;

    @Override
    public List<Address> list() {
        return repository.findAll();
    }
}
