package com.company.banksystem.service;

import com.company.banksystem.entity.Property;
import com.company.banksystem.repository.PropertyRepo;
import com.company.banksystem.service.interfaces.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class PropertyServiceImpl implements PropertyService {
    @Autowired
    private PropertyRepo propertyRepo;

    @Override
    public Property create(Property property) {
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
