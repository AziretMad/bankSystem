package com.company.banksystem.bootstrap;

import com.company.banksystem.entity.Client;
import com.company.banksystem.entity.ClientRoles;
import com.company.banksystem.repository.ClientRolesRepo;
import com.company.banksystem.service.ClientServiceImpl;
import com.company.banksystem.service.CreditServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {
    @Autowired
    private ClientServiceImpl clientService;
    @Autowired
    private ClientRolesRepo clientRolesRepo;
    @Autowired
    private CreditServiceImpl creditService;

    @Override
    public void run(String... args) throws Exception {
        Client client = Client.builder().fullName("user")
                .password("456")
                .isActive(1).build();
        ClientRoles cl = ClientRoles.builder()
                .client(client)
                .roleName("ROLE_CLIENT")
                .build();
        Client clientModel1 = Client.builder()
                .fullName("admin")
                .password("123")
                .isActive(1)
                .build();
        ClientRoles cl1 = ClientRoles.builder()
                .roleName("ROLE_ADMIN")
                .client(clientModel1)
                .build();
        clientService.update(client);
        clientService.update(clientModel1);

        clientRolesRepo.save(cl);
        clientRolesRepo.save(cl1);
    }
}
