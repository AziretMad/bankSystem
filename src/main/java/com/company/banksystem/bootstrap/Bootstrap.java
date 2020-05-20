package com.company.banksystem.bootstrap;

import com.company.banksystem.entity.ClientRoles;
import com.company.banksystem.models.ClientModel;
import com.company.banksystem.repository.ClientRolesRepo;
import com.company.banksystem.service.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ClientServiceImpl clientService;
    @Autowired
    private ClientRolesRepo clientRolesRepo;
    @Override
    public void run(String... args) throws Exception {
        ClientModel clientModel = ClientModel.builder()
                .fullName("Alex")
                .address("telcel 12/1")
                .telephone("123456")
                .password(passwordEncoder.encode("1111"))
                .inn("321").build();
        ClientRoles cl = ClientRoles.builder()
                .client(clientService.create(clientModel))
                .RoleName("ROLE_CLIENT")
                .build();
        ClientModel clientModel1 = ClientModel.builder()
                .fullName("admin")
                .password(passwordEncoder.encode("999"))
                .build();
        ClientRoles cl1 = ClientRoles.builder()
                .RoleName("ROLE_ADMIN")
                .client(clientService.create(clientModel1))
                .build();
        clientRolesRepo.save(cl);
        clientRolesRepo.save(cl1);
    }
}
