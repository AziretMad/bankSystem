package com.company.banksystem.service.interfaces;

import java.util.List;

public interface BaseService<T, J>{
    T create(J entity);
    T getById(Long id);
    List<T> getAll();
    void delete(Long id);
    T update(T entity);
}
