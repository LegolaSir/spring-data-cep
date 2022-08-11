package com.digitalinnovation.springdatacpf.controller;

import com.digitalinnovation.springdatacpf.entity.form.ClientForm;
import com.digitalinnovation.springdatacpf.exception.FormEmptyFieldException;
import com.digitalinnovation.springdatacpf.exception.ZipcodeNotFoundException;
import com.digitalinnovation.springdatacpf.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService service;

    @PostMapping("/insert")
    public ResponseEntity insert(@Valid @RequestBody ClientForm form){
        StringBuilder message = new StringBuilder();

        try
        {
            service.insert(form);
            message.append("ELEMENT: <")
                    .append(form.getName())
                    .append("> RELATED TO ZIPCODE: <")
                    .append(form.getZipCode())
                    .append("> HAS BEEN INSERTED INTO DATABA tb_clients");

            return ResponseEntity.ok().body(message);
        }
        catch (FormEmptyFieldException e){
            message.append("FAILED TO INSERT ELEMENT BECAUSE <")
                    .append(e.getMessage())
                    .append("> IS EMPTY");
        }
        catch (ZipcodeNotFoundException e){
            message.append("FAILED TO INSERT ELEMENT BECAUSE ZIPCODE <")
                    .append(e.getMessage())
                    .append("> IS INVALID");
        }

        return ResponseEntity.badRequest().body(message);
    }
}
