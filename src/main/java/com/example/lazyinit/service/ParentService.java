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
public class ParentService {

    private final ParentRepository parentRepository;
    private final ChildService childService;

    public ParentService(ParentRepository parentRepository, ChildService childService) {
        this.parentRepository = parentRepository;
        this.childService = childService;
    }

    public Parent create(String parentName, String childName) {
        Child child = childService.create(childName);

        Parent parent = new Parent();
        parent.setName(parentName);
        parent.setChild(child);
        return parentRepository.save(parent);
    }

    public Parent retrieve(UUID id) {
        Parent parent = parentRepository.findById(id).get();
        return parent;
    }

}
