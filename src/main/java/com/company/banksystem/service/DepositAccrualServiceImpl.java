package com.company.banksystem.service;

import com.company.banksystem.entity.BankAccount;
import com.company.banksystem.entity.Deposit;
import com.company.banksystem.entity.actions.DepositAccrual;
import com.company.banksystem.enums.DepositType;
import com.company.banksystem.enums.TransactionStatus;
import com.company.banksystem.exceptions.NotFoundBankAccount;
import com.company.banksystem.exceptions.NotFoundDeposit;
import com.company.banksystem.exceptions.WrongConfirmationCode;
import com.company.banksystem.models.Confirmation;
import com.company.banksystem.models.actions.DepositAccrualModel;
import com.company.banksystem.repository.DepositAccrualRepo;
import com.company.banksystem.service.interfaces.BankAccountService;
import com.company.banksystem.service.interfaces.DepositAccrualService;
import com.company.banksystem.service.interfaces.DepositService;
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

    @Override
    public DepositAccrual create(DepositAccrualModel entity) throws Exception {
        Deposit deposit = depositService.getById(entity.getDeposit().getId());
        BankAccount bankAccount=bankAccountService.getById(entity.getBankAccount().getId());
        if (deposit != null&&bankAccount!=null) {
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
        return depositAccrualRepo.findAllByDeposit_Id(id);
    }

    @Override
    public DepositAccrual setAccrualByDepositType(Long depositId) throws Exception {
        Deposit deposit = depositService.getById(depositId);
        if (deposit != null) {
            DepositType depositType = deposit.getDepositType();
            DepositAccrual depositAccrual = findByDepositId(deposit.getId());
            BankAccount bankAccount = bankAccountService.getById(depositAccrual.getBankAccount().getId());
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
                    if (bankAccount != null) {
                        //accrued interest is added to other bank account
                        bankAccount.setAmount(bankAccount.getAmount().add(accrualCalculate(deposit)));
                        depositAccrual.setStatus(TransactionStatus.OK);
                        depositAccrual.setAmount(accrualCalculate(deposit));
                    }
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

    public DepositAccrual confirmationDeposit(Confirmation confirmation) throws Exception {
        DepositAccrual depositAccrual = getById(confirmation.getTransactionId());
        if (depositAccrual != null) {
            if (depositAccrual.getCode().equals(confirmation.getConfirmationCode()))
                return setAccrualByDepositType(depositAccrual.getDeposit().getId());
            else throw new WrongConfirmationCode();
        } else throw new NotFoundDeposit();
    }

}
