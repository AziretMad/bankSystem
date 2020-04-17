package com.company.banksystem.service;

import com.company.banksystem.entity.Client;
import com.company.banksystem.repository.ClientRepo;
import com.company.banksystem.service.interfaces.ClientService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientRepo clientRepo;
    @Override
    public Client create(Client client) {
        return clientRepo.save(client) ;
    }

    @Override
    public Client getById(Long id) {
        Optional<Client>client=clientRepo.findById(id);
        return client.get();
    }

    @Override
    public List<Client> getAll() {
        return clientRepo.findAll();
    }

    @Override
    public void delete(Long id) {
    clientRepo.deleteById(id);
    }
}