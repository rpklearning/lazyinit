package com.example.lazyinit.controller;

import com.example.lazyinit.entity.Parent;
import com.example.lazyinit.model.ParentModel;
import com.example.lazyinit.service.ParentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ParentController {

    final ParentService parentService;

    public ParentController(ParentService parentService) {
        this.parentService = parentService;
    }

    @PostMapping("/parents/lazyinitexception")
    public void generateLazyInitException() {
        Parent parent = parentService.create("some", "thing");
        Parent fetchedParent = parentService.retrieve(parent.getId());
        fetchedParent.getChild().getName(); // should throw lazy-init-exception
        /**
         * How to fix the exception?
         * 1. Autowire ChildService
         * 2. Invoke childService.retrieve(fetchedParent.getChild().getId())
         * 3. Call getName() on the retrieved child object
         */
    }

}
