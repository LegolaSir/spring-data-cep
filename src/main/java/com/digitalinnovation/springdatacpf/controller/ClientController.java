package com.digitalinnovation.springdatacpf.controller;

import com.digitalinnovation.springdatacpf.entity.form.ClientForm;
import com.digitalinnovation.springdatacpf.exception.ClientNotFoundException;
import com.digitalinnovation.springdatacpf.exception.FormEmptyFieldException;
import com.digitalinnovation.springdatacpf.exception.ZipcodeNotFoundException;
import com.digitalinnovation.springdatacpf.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
                    .append("> HAS BEEN INSERTED INTO DATABASE tb_clients");

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

    @GetMapping("/list")
    public ResponseEntity list(){
        return ResponseEntity.ok().body(service.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity getClient(@PathVariable(value = "id") Long id){
        try
        {
            return ResponseEntity.ok(service.getById(id));
        }
        catch (ClientNotFoundException e)
        {
            return ResponseEntity.badRequest().body("CLIENT ID <" + id + "> NOT FOUND IN DATABASE tb_clients");
        }
    }

    @PutMapping("/{id}/update")
    public ResponseEntity updateClient(@PathVariable(value = "id") Long id,
                                       @Valid @RequestBody ClientForm form)
    {
        StringBuilder message = new StringBuilder();

        try
        {
            service.update(id, form);
            message.append("CLIENT ID <")
                    .append(id)
                    .append("> UPDATED IN DATABASE tb_clients");

            return ResponseEntity.ok().body(message);
        }
        catch (ClientNotFoundException e)
        {
            message.append("CLIENT ID <")
                    .append(id)
                    .append("> NOT FOUND IN DATABASE tb_clients");
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
