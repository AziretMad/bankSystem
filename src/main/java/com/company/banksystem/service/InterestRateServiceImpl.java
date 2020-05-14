package com.company.banksystem.service;

import com.company.banksystem.entity.InterestRateDeposit;
import com.company.banksystem.enums.Currency;
import com.company.banksystem.exceptions.NotFoundDeposit;
import com.company.banksystem.exceptions.NotFoundInterestRate;
import com.company.banksystem.models.InterestRateModel;
import com.company.banksystem.repository.InterestRateRepository;
import com.company.banksystem.service.interfaces.InterestRateService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InterestRateServiceImpl implements InterestRateService {
    @Autowired
    private InterestRateRepository rateRepository;

    @Override
    public InterestRateDeposit getById(Long id) {
        Optional<InterestRateDeposit> rateDeposits = rateRepository.findById(id);
        return rateDeposits.get();
    }

    @Override
    public List<InterestRateDeposit> findAllByCurrency(Currency currency) throws NotFoundInterestRate {
        List<InterestRateDeposit> findAll = rateRepository.findAllByCurrency(currency);
        if (findAll != null)
            return findAll;
        throw new NotFoundInterestRate();
    }

    @Override
    public List<InterestRateDeposit> findAllByDuration(Integer duration) throws NotFoundInterestRate {
        List<InterestRateDeposit> findAllByDuration = rateRepository.findAllByDuration(duration);
        if (findAllByDuration != null)
            return findAllByDuration;
        throw new NotFoundInterestRate();
    }

    @Override
    public InterestRateDeposit findByCurrencyAndDuration(Currency currency, Integer duration) throws NotFoundInterestRate {
        InterestRateDeposit findInterestRate = rateRepository.findByCurrencyAndDuration(currency, duration);
        if (findInterestRate != null)
            return findInterestRate;
        throw new NotFoundInterestRate();
    }

    @Override
    public List<InterestRateDeposit> getAll() {
        return rateRepository.findAll();
    }

    @SneakyThrows
    @Override
    public void delete(Long id)  {
        InterestRateDeposit rateDeposit = getById(id);
        if (rateDeposit != null)
            rateRepository.delete(rateDeposit);
        throw new NotFoundDeposit();
    }

    @Override
    public InterestRateDeposit update(InterestRateDeposit entity) {
        return rateRepository.save(entity);
    }

    @Override
    public InterestRateDeposit create(InterestRateModel entity) throws Exception {
        InterestRateDeposit rateDeposit = InterestRateDeposit.builder()
                .currency(entity.getCurrency())
                .duration(entity.getDuration())
                .interestRate(entity.getInterestRate()).build();
        return rateRepository.save(rateDeposit);
    }
}