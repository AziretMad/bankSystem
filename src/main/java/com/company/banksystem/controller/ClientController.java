package com.company.banksystem.controller;

import com.company.banksystem.entity.Client;
import com.company.banksystem.models.ClientModel;
import com.company.banksystem.service.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    private ClientServiceImpl clientService;

    @GetMapping
    public List<Client> getAll() {
        return clientService.getAll();
    }

    @GetMapping("/{id}")
    public Client getById(@PathVariable("id") Long id) {
        return clientService.getById(id);
    }

    @PostMapping
    public Client create(@RequestBody ClientModel client) {
        return clientService.create(client);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        clientService.delete(id);
    }
}
