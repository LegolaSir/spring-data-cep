package com.digitalinnovation.springdatacpf.service;

import com.digitalinnovation.springdatacpf.entity.Client;
import com.digitalinnovation.springdatacpf.entity.form.ClientForm;
import com.digitalinnovation.springdatacpf.exception.ZipcodeNotFoundException;

import java.util.List;

public interface ClientService {

    void insert(ClientForm form) throws ZipcodeNotFoundException;

    List<Client> list();

    Client getById(Long id);

    void update(Long id, ClientForm form) throws ZipcodeNotFoundException;
}
