package com.digitalinnovation.springdatacpf.service.impl;

import com.digitalinnovation.springdatacpf.entity.Client;
import com.digitalinnovation.springdatacpf.entity.form.ClientForm;
import com.digitalinnovation.springdatacpf.repository.ClientRepository;
import com.digitalinnovation.springdatacpf.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    // TODO: Implement insert()
    // TODO: Figure out how to access ViaCEP Info. [Convert String to JSON format]

    @Autowired
    private ClientRepository repository;

    @Override
    public void insert(ClientForm form) {

    }

    @Override
    public List<Client> list() {
        return null;
    }

    @Override
    public Client getById(Long id) {
        return null;
    }
}
