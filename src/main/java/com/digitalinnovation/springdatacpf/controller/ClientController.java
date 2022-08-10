package com.digitalinnovation.springdatacpf.controller;

import com.digitalinnovation.springdatacpf.entity.form.ClientForm;
import com.digitalinnovation.springdatacpf.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService service;

    @PostMapping("/insert")
    public ResponseEntity insert(@Valid ClientForm form){
        service.insert(form);

        return ResponseEntity.ok("ELEMENT INSERTED INTO DATABASE");
    }
}
