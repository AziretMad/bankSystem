package com.company.banksystem.service;

import com.company.banksystem.entity.Client;
import com.company.banksystem.entity.Credit;
import com.company.banksystem.entity.Property;
import com.company.banksystem.models.ClientModel;
import com.company.banksystem.models.CreditModel;
import com.company.banksystem.models.PropertyModel;
import com.company.banksystem.repository.PropertyRepo;
import com.company.banksystem.service.interfaces.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class PropertyServiceImpl implements PropertyService {
    @Autowired
    private PropertyRepo propertyRepo;

    @Override
    public Property create(PropertyModel propertyModel) {
        ClientModel clientModel = propertyModel.getClientModel();
        Client client = Client.builder().fullName(clientModel.getFullName())
                .address(clientModel.getAddress())
                .telephone(clientModel.getTelephone())
                .build();
        CreditModel creditModel = propertyModel.getCreditModel();
        Credit credit = Credit.builder()
                .creditNumber(creditModel.getCreditNumber())
                .amount(creditModel.getAmount())
                .interestRate(creditModel.getInterestRate())
                .dateOfCreation(creditModel.getDateOfCreation())
                .dateOfClosing(creditModel.getDateOfClosing())
                .duration(creditModel.getDuration())
                .client(client)
                .build();
        Property property = Property.builder()
                .price(propertyModel.getPrice())
                .description(propertyModel.getDescription())
                .client(client)
                .credit(credit)
                .build();
        return propertyRepo.save(property);
    }

    @Override
    public Property getById(Long id) {
        Optional<Property>property=propertyRepo.findById(id);
        return property.get();
    }

    @Override
    public List<Property> getAll() {
        return propertyRepo.findAll();
    }

    @Override
    public void delete(Long id) {
        propertyRepo.deleteById(id);
    }
}
