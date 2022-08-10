package com.digitalinnovation.springdatacpf.service;

import com.digitalinnovation.springdatacpf.entity.Client;
import com.digitalinnovation.springdatacpf.entity.form.ClientForm;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ClientService {

    void insert(ClientForm form);

    List<Client> list();

    Client getById(Long id);
}
