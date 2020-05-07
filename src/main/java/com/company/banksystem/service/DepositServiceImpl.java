package com.company.banksystem.service;

import com.company.banksystem.entity.Client;
import com.company.banksystem.entity.Deposit;
import com.company.banksystem.enums.Currency;
import com.company.banksystem.enums.Status;
import com.company.banksystem.exceptions.NotFoundClient;
import com.company.banksystem.exceptions.WrongEnteredCurrency;
import com.company.banksystem.exceptions.WrongEnteredDuration;
import com.company.banksystem.models.DepositModel;
import com.company.banksystem.repository.DepositRepo;
import com.company.banksystem.service.interfaces.ClientService;
import com.company.banksystem.service.interfaces.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepositServiceImpl implements DepositService {
    @Autowired
    private DepositRepo depositRepo;
    @Autowired
    private ClientService clientService;

    @Override
    public Deposit create(DepositModel depositModel) throws Exception {
        Client client = clientService.getById(depositModel.getClient().getId());
        if (client != null) {
            Deposit deposit = Deposit.builder()
                    .amount(depositModel.getAmount())
                    .duration(depositModel.getDuration())
                    .depositType(depositModel.getDepositType())
                    .client(client)
                    .status(Status.INACTIVE)
                    .currency(depositModel.getCurrency())
                    .build();
            //setInterestRate(deposit);
            return depositRepo.save(setInterestRate(deposit));
        } else throw new NotFoundClient();
    }

    @Override
    public Deposit getById(Long id) {
        Optional<Deposit> deposit = depositRepo.findById(id);
        return deposit.get();
    }

    @Override
    public List<Deposit> getAll() {
        return depositRepo.findAll();
    }

    @Override
    public void delete(Long id) {
        depositRepo.deleteById(id);
    }

    @Override
    public Deposit update(Deposit entity) {
        return depositRepo.save(entity);
    }

    @Override //our interest rate is fixed
    public Deposit setInterestRate(Deposit deposit) throws Exception {
        Status status = deposit.getStatus();
        Integer duration = deposit.getDuration();
        Currency currency = deposit.getCurrency();
        if (status.equals(Status.INACTIVE)) {
            switch (duration) {
                case 3:
                    switch (currency) {
                        case KGS:
                            deposit.setInterestRate(3.0);
                            break;
                        case EUR:
                            deposit.setInterestRate(0.5);
                            break;
                        case USD:
                            deposit.setInterestRate(1.5);
                            break;
                        case RUB:
                            deposit.setInterestRate(4.0);
                            break;
                        default:
                            throw new WrongEnteredCurrency();
                    }
                    break;
                case 6:
                    switch (currency) {
                        case KGS:
                            deposit.setInterestRate(6.0);
                            break;
                        case EUR:
                            deposit.setInterestRate(1.0);
                            break;
                        case USD:
                            deposit.setInterestRate(2.0);
                            break;
                        case RUB:
                            deposit.setInterestRate(4.5);
                            break;
                        default:
                            throw new WrongEnteredCurrency();
                    }
                    break;
                case 9:
                    switch (currency) {
                        case KGS:
                            deposit.setInterestRate(7.5);
                            break;
                        case EUR:
                            deposit.setInterestRate(1.5);
                            break;
                        case USD:
                            deposit.setInterestRate(3.0);
                            break;
                        case RUB:
                            deposit.setInterestRate(5.0);
                            break;
                        default:
                            throw new WrongEnteredCurrency();
                    }
                    break;
                case 12:
                    switch (currency) {
                        case KGS:
                            deposit.setInterestRate(9.5);
                            break;
                        case USD:
                            deposit.setInterestRate(3.5);
                            break;
                        case EUR:
                            deposit.setInterestRate(2.0);
                            break;
                        case RUB:
                            deposit.setInterestRate(6.5);
                            break;
                        default:
                            throw new WrongEnteredCurrency();
                    }
                    break;
                case 18:
                    switch (currency) {
                        case KGS:
                            deposit.setInterestRate(10.0);
                            break;
                        case USD:
                            deposit.setInterestRate(4.0);
                            break;
                        case EUR:
                            deposit.setInterestRate(2.5);
                            break;
                        case RUB:
                            deposit.setInterestRate(7.0);
                            break;
                        default:
                            throw new WrongEnteredCurrency();
                    }
                    break;
                case 24:
                    switch (currency) {
                        case KGS:
                            deposit.setInterestRate(10.5);
                            break;
                        case USD:
                            deposit.setInterestRate(4.0);
                            break;
                        case EUR:
                            deposit.setInterestRate(2.5);
                            break;
                        case RUB:
                            deposit.setInterestRate(8.0);
                            break;
                        default:
                            throw new WrongEnteredCurrency();
                    }
                    break;
                case 36:
                case 48:
                case 60:
                    switch (currency) {
                        case KGS:
                            deposit.setInterestRate(11.0);
                            break;
                        case USD:
                            deposit.setInterestRate(4.5);
                            break;
                        case EUR:
                            deposit.setInterestRate(2.5);
                            break;
                        case RUB:
                            deposit.setInterestRate(9.0);
                            break;
                        default:
                            throw new WrongEnteredCurrency();
                    }
                    break;
                default:
                    throw new WrongEnteredDuration();
            }

        }
        return deposit;
    }


}
