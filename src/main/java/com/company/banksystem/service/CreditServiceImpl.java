package com.company.banksystem.service;

import com.company.banksystem.entity.Client;
import com.company.banksystem.entity.Credit;
import com.company.banksystem.enums.Currency;
import com.company.banksystem.exceptions.NotFoundClient;
import com.company.banksystem.exceptions.NotFoundInterestRate;
import com.company.banksystem.models.CreditModel;
import com.company.banksystem.repository.CreditRepo;
import com.company.banksystem.service.interfaces.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import static com.company.banksystem.service.BankAccountServiceImpl.luhnAlgorithm;

@Service
public class CreditServiceImpl implements CreditService {
    @Autowired
    private CreditRepo creditRepo;
    @Autowired
    private ClientServiceImpl clientService;

    @Override
    public Credit create(CreditModel creditModel) throws Exception {
        Client client = clientService.getById(creditModel.getClient().getId());
        if (client != null) {
            Credit credit = Credit.builder()
                    .creditNumber(generateCreditNumber())
                    .amount(creditModel.getAmount())
                    .interestRate(setInterestCredit(creditModel))
                    .creditType(creditModel.getCreditType())
                    .duration(creditModel.getDuration())
                    .client(client)
                    .currency(creditModel.getCurrency())
                    .build();
            return creditRepo.save(credit);
        } else throw new NotFoundClient();
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

    private Double setInterestCredit(CreditModel credit) throws NotFoundInterestRate {
        Currency currency = credit.getCurrency();
        switch (currency) {
            case KGS: {
                return 25.0;
            }
            case USD:
            case EUR: {
                return 14.0;
            }
            case RUB: {
                return 21.5;
            }
            case GBH:
            case JPY: {
                return 16.5;
            }
            default:
                throw new NotFoundInterestRate();
        }

    }
}
