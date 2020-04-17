package com.company.banksystem.service.interfaces;

import java.util.List;

public interface BaseService<T>{
    T create(T entity);
    T getById(Long id);
    List<T> getAll();
    void delete(Long id);
}
