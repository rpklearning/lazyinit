package com.example.lazyinit.service;

import com.example.lazyinit.entity.Child;
import com.example.lazyinit.entity.Parent;
import com.example.lazyinit.repository.ChildRepository;
import com.example.lazyinit.repository.ParentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

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
