package com.company.banksystem.controller;

import com.company.banksystem.entity.Property;
import com.company.banksystem.models.PropertyModel;
import com.company.banksystem.service.PropertyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/property")
public class PropertyController {
    @Autowired
    private PropertyServiceImpl propertyService;

    @GetMapping
    public ResponseEntity getAll() {
        try {
            List<Property> list = propertyService.getAll();
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable("id") Long id) {
        try {
            Property property = propertyService.getById(id);
            return new ResponseEntity<>(property, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity create(@RequestBody PropertyModel propertyModel) throws Exception {
        try {
            Property property = propertyService.create(propertyModel);
            return new ResponseEntity<>(property, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        try {
            propertyService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
