package com.company.banksystem.service.interfaces;

import com.company.banksystem.entity.Property;
import com.company.banksystem.models.PropertyModel;

import java.math.BigDecimal;

public interface PropertyService extends BaseService<Property, PropertyModel> {
    public BigDecimal setValuationPrice(BigDecimal marketPrice);
}
