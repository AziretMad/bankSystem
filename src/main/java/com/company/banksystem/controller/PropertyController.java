package com.company.banksystem.controller;

import com.company.banksystem.entity.Property;
import com.company.banksystem.service.PropertyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/property")
public class PropertyController {
    @Autowired
    private PropertyServiceImpl propertyService;
    @GetMapping
    public List<Property> getAll() {
        return propertyService.getAll();
    }

    @GetMapping("/{id}")
    public Property getById(@PathVariable("id") Long id) {
        return propertyService.getById(id);
    }

    @PostMapping
    public Property create(@RequestBody Property bankAccount) {
        return propertyService.create(bankAccount);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        propertyService.delete(id);
    }
}
