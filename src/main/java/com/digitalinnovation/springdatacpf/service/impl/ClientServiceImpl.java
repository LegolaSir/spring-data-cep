package com.digitalinnovation.springdatacpf.service.impl;

import com.digitalinnovation.springdatacpf.entity.Address;
import com.digitalinnovation.springdatacpf.entity.Client;
import com.digitalinnovation.springdatacpf.entity.form.ClientForm;
import com.digitalinnovation.springdatacpf.exception.ClientNotFoundException;
import com.digitalinnovation.springdatacpf.exception.FormEmptyFieldException;
import com.digitalinnovation.springdatacpf.exception.ZipcodeNotFoundException;
import com.digitalinnovation.springdatacpf.repository.AddressRepository;
import com.digitalinnovation.springdatacpf.repository.ClientRepository;
import com.digitalinnovation.springdatacpf.service.ClientService;
import com.digitalinnovation.springdatacpf.service.ViaCepService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ViaCepService viaCepService;

    @Override
    public void insert(ClientForm form) throws ZipcodeNotFoundException {
        persistClientByZipcode(form, Optional.empty());
    }

    @Override
    public List<Client> list() {
        return clientRepository.findAll();
    }

    @Override
    public Client getById(Long id) {
        Client client = clientRepository.findById(id).orElseGet(() -> {
            return null;
        });
        if(client == null) throw new ClientNotFoundException();

        return client;
    }

    @Override
    public void update(Long id, ClientForm form) throws ZipcodeNotFoundException {
        Optional<Client> clientDb = clientRepository.findById(id);

        if(clientDb.isPresent()) {
            persistClientByZipcode(form, Optional.of(id));
        }
        else {
            throw new ClientNotFoundException();
        }
    }

    private void persistClientByZipcode(ClientForm form, Optional<Long> clientId) throws ZipcodeNotFoundException {
        if(form.getName().isEmpty()) throw new FormEmptyFieldException("Name Field");
        if(form.getZipCode().isEmpty()) throw new FormEmptyFieldException("Zipcode Field");

        String zipCode = form.getZipCode();
        Client item = new Client();

        if(clientId.isPresent())
            item.setId(clientId.get());

        item.setName(form.getName());

        Address address = addressRepository.findById(zipCode).orElseGet(() -> {
            String feedbackStr = viaCepService.searchCep(zipCode);
            if(feedbackStr.isEmpty()) return null;

            JSONObject feedbackJson = new JSONObject(feedbackStr);
            if(feedbackJson.optBoolean("erro")) return null;

            Address addressFound = buildAddressByJson(feedbackJson);

            addressRepository.save(addressFound);
            return addressFound;
        });
        if(address == null) throw new ZipcodeNotFoundException(zipCode);

        item.setAddress(address);
        clientRepository.save(item);
    }

    private Address buildAddressByJson(JSONObject json){
        Address address = new Address();

        address.setZipCode(json.getString("cep"));
        address.setAddressName(json.getString("logradouro"));
        address.setNeighborhood(json.getString("bairro"));
        address.setCity(json.getString("localidade"));
        address.setProvince(json.getString("uf"));
        address.setAreaCode(json.getString("ddd"));
        address.setAdditionalInfo(json.optString("complemento"));

        return address;
    }
}
