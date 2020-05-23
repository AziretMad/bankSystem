package com.company.banksystem.service;

import com.company.banksystem.entity.BankAccount;
import com.company.banksystem.entity.Deposit;
import com.company.banksystem.entity.actions.DepositAccrual;
import com.company.banksystem.entity.actions.Exchange;
import com.company.banksystem.enums.Currency;
import com.company.banksystem.enums.DepositType;
import com.company.banksystem.enums.Status;
import com.company.banksystem.enums.TransactionStatus;
import com.company.banksystem.exceptions.NotFoundBankAccount;
import com.company.banksystem.exceptions.NotFoundDeposit;
import com.company.banksystem.models.actions.DepositAccrualModel;
import com.company.banksystem.models.actions.ExchangeModel;
import com.company.banksystem.repository.DepositAccrualRepo;
import com.company.banksystem.service.interfaces.BankAccountService;
import com.company.banksystem.service.interfaces.DepositAccrualService;
import com.company.banksystem.service.interfaces.DepositService;
import com.company.banksystem.service.interfaces.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DepositAccrualServiceImpl implements DepositAccrualService {
    @Autowired
    private DepositAccrualRepo depositAccrualRepo;
    @Autowired
    private DepositService depositService;
    @Autowired
    private BankAccountService bankAccountService;
    @Autowired
    private ExchangeService exchangeService;

    @Override
    public DepositAccrual create(DepositAccrualModel entity) throws Exception {
        Deposit deposit = depositService.getById(entity.getDeposit().getId());
        BankAccount bankAccount = bankAccountService.getById(entity.getBankAccount().getId());
        if (deposit != null && bankAccount != null) {
            DepositAccrual depositAccrual = DepositAccrual.builder()
                    .status(TransactionStatus.AWAITING_PROCESS)
                    .deposit(deposit).bankAccount(bankAccount)
                    .date(new Date()).build();
            return depositAccrualRepo.save(depositAccrual);
        } else throw new NotFoundDeposit();
    }

    @Override
    public DepositAccrual getById(Long id) {
        Optional<DepositAccrual> depositAccrual = depositAccrualRepo.findById(id);
        return depositAccrual.get();
    }

    @Override
    public List<DepositAccrual> getAll() {
        return depositAccrualRepo.findAll();
    }

    @Override
    public void delete(Long id) {
        depositAccrualRepo.deleteById(id);
    }

    @Override
    public DepositAccrual update(DepositAccrual entity) {
        return depositAccrualRepo.save(entity);
    }

    @Override
    public DepositAccrual findByDepositId(Long id) {
        return depositAccrualRepo.getByDepositId(id);
    }

    @Override
    public DepositAccrual setAccrualByDepositType(Long depositId) throws Exception {
        Deposit deposit = depositService.getById(depositId);
        if (deposit != null&&deposit.getStatus().equals(Status.ACTIVE)) {
            DepositType depositType = deposit.getDepositType();
            DepositAccrual depositAccrual = findByDepositId(depositId);
                switch (depositType) {
                    case SAVINGS:
                    case DEMAND:
                        //'cause accrued interest is added to the principal amount
                        BigDecimal action = accrualCalculate(deposit).add(deposit.getAmount());
                        depositAccrual.getDeposit().setAmount(action);
                        depositAccrual.setAmount(accrualCalculate(deposit));
                        depositAccrual.setStatus(TransactionStatus.OK);
                        break;
                    case CUMULATIVE:
                        BankAccount bankAccount = bankAccountService.getById(depositAccrual.getBankAccount().getId());
                        if (bankAccount != null&& bankAccount.getStatus().equals(Status.ACTIVE)) {
                            //accrued interest is added to other bank account
                            bankAccount.setAmount(bankAccount.getAmount()
                                    .add(saveExchange(deposit.getCurrency(),bankAccount.getCurrency(),accrualCalculate(deposit))));
                            depositAccrual.setStatus(TransactionStatus.OK);
                            depositAccrual.setAmount(accrualCalculate(deposit));
                        } else
                        throw new NotFoundBankAccount();
                }
            return depositAccrual;
        } else throw new NotFoundDeposit();
    }

    @Override
    public BigDecimal accrualCalculate(Deposit deposit) {
        BigDecimal percent = BigDecimal.valueOf(deposit.getInterestRate());
        BigDecimal amount = deposit.getAmount();
        Integer duration = deposit.getDuration();
        //Начисление=amount*percent*duration/365/100; длительность в месяцах
        BigDecimal action = amount.multiply(percent).multiply(BigDecimal.valueOf(duration * 30.5))
                .divide(BigDecimal.valueOf(366), 0, RoundingMode.HALF_DOWN)
                .divide(BigDecimal.valueOf(100), 0, RoundingMode.HALF_DOWN);
        return action;
    }
    private BigDecimal saveExchange(Currency accountFrom, Currency accountTo, BigDecimal amount) throws Exception {
        ExchangeModel model = ExchangeModel.builder().currencyFrom(accountFrom)
                .currencyTo(accountTo).amount(amount).build();
        Exchange exchange = exchangeService.create(model);
        return exchangeService.exchange(model);
    }
}
