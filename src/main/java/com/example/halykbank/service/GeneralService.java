package com.example.halykbank.service;

import java.util.List;
import java.util.Optional;

public interface GeneralService<D,T> {
    D save(D object);

    List<D> findAll();

    D update(D object);

    void delete(T id);

    Optional<D> findById(T id);
}
