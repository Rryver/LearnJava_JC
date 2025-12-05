package com.kolosov.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
public abstract class CrudService<ENTITY, ID> {

    private final JpaRepository<ENTITY, ID> jpaRepository;

    @Transactional(readOnly = true)
    public List<ENTITY> getAll() {
        return jpaRepository.findAll().stream()
                .toList();
    }

    @Transactional
    public ENTITY save(ENTITY entity) {
        return jpaRepository.save(entity);
    }

    @Transactional(readOnly = true)
    public ENTITY getById(ID id) {
        return jpaRepository.findById(id).orElse(null);
    }

    @Transactional
    public void deleteById(ID id) {
        jpaRepository.deleteById(id);
    }

}
