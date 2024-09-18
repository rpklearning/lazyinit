package com.example.lazyinit.service;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.lazyinit.entity.Child;
import com.example.lazyinit.repository.ChildRepository;

@Transactional
@Service
public class ChildService {

    private final ChildRepository childRepository;

    public ChildService(ChildRepository childRepository) {
        this.childRepository = childRepository;
    }

    public Child create(String name) {
        Child child = new Child();
        child.setName(name);
        return childRepository.save(child);

    }

    public Child retrieve(UUID id) {
        return childRepository.findById(id).get();
    }

}
