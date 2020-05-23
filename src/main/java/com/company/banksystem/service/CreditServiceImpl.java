package com.company.banksystem.service;

import com.company.banksystem.entity.Client;
import com.company.banksystem.entity.Credit;
import com.company.banksystem.exceptions.NotFoundClient;
import com.company.banksystem.models.CreditModel;
import com.company.banksystem.repository.CreditRepo;
import com.company.banksystem.service.interfaces.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.company.banksystem.service.BankAccountServiceImpl.luhnAlgorithm;

@Service
public class CreditServiceImpl implements CreditService {
    @Autowired
    private CreditRepo creditRepo;
    @Autowired
    private ClientServiceImpl clientService;

    @Override
    public Credit create(CreditModel creditModel) throws Exception {
        Client client = clientService.getById(creditModel.getClientModelId());
        if (client != null) {
            Credit credit = Credit.builder()
                    .creditNumber(generateCreditNumber())
                    .amount(creditModel.getAmount())
                    .interestRate(creditModel.getInterestRate())
                    .creditType(creditModel.getCreditType())
                    .duration(creditModel.getDuration())
                    .client(client)
                    .build();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(credit.getCreatedDate());
            calendar.add(Calendar.MONTH, credit.getDuration());
            credit.setClosedDate(calendar.getTime());
            return creditRepo.save(credit);
        }else throw  new NotFoundClient();
    }

    @Override
    public Credit getById(Long id) {
        Optional<Credit> credit = creditRepo.findById(id);
        return credit.get();
    }

    @Override
    public List<Credit> getAll() {
        return creditRepo.findAll();
    }

    @Override
    public void delete(Long id) {
        creditRepo.deleteById(id);
    }

    @Override
    public Credit update(Credit entity) {
        return creditRepo.save(entity);
    }

    @Override
    public String generateCreditNumber() {
        Random r = new Random();
        String depositNumber = "247203";//2-платежная система 47203 - идентификатор банка
        int accountCode = r.nextInt((999999999 - 100000000) + 1) + 100000000;//идентификатор аккаунта
        String number = depositNumber + String.valueOf(accountCode);
        Integer luhn = luhnAlgorithms(number);
        return number + luhn;
    }

    @Override
    public Integer luhnAlgorithms(String code) {
        return luhnAlgorithm(code);
    }

    public Credit findCreditByNumber(String number){
         return creditRepo.findCreditByCreditNumber(number);
    }

    public List<Credit> findAllCreditByClientId(Long id){
        return creditRepo.findAllCreditsByClientId(id);
    }


}
