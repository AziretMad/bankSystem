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
@Autowired
private ClientServiceImpl clientService;
@Autowired
private CreditServiceImpl creditService;
    @Override
    public Property create(PropertyModel propertyModel) {
        Client client = clientService.getById(propertyModel.getClientModel().getId());
        Credit credit = creditService.getById(propertyModel.getCreditModel().getId());
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

    @Override
    public Property update(Property entity) {
        return propertyRepo.save(entity);
    }
}
