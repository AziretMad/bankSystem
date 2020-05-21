package com.company.banksystem.bootstrap;

import com.company.banksystem.entity.ClientRoles;
import com.company.banksystem.entity.Credit;
import com.company.banksystem.enums.CreditPaymentType;
import com.company.banksystem.models.ClientModel;
import com.company.banksystem.models.CreditModel;
import com.company.banksystem.repository.ClientRolesRepo;
import com.company.banksystem.service.ClientServiceImpl;
import com.company.banksystem.service.CreditServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

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
        ClientModel clientModel = ClientModel.builder()
                .fullName("Alex")
                .address("telcel 12/1")
                .telephone("123456")
                .inn("321").build();
        ClientRoles cl = ClientRoles.builder()
                .client(clientService.create(clientModel))
                .roleName("ROLE_CLIENT")
                .build();
        ClientModel clientModel1 = ClientModel.builder()
                .fullName("admin")
                .build();
        ClientRoles cl1 = ClientRoles.builder()
                .roleName("ROLE_ADMIN")
                .client(clientService.create(clientModel1))
                .build();
        clientRolesRepo.save(cl);
        clientRolesRepo.save(cl1);
    }
}
