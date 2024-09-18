package com.example.lazyinit.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lazyinit.entity.Child;
import com.example.lazyinit.entity.Parent;
import com.example.lazyinit.service.ChildService;
import com.example.lazyinit.service.ParentService;

@RestController
public class ParentController {

    final ParentService parentService;
    final ChildService childService;

    public ParentController(ParentService parentService, ChildService childService) {
        this.parentService = parentService;
        this.childService = childService;
    }

    /**
     * This method will throw lazy-init-exception. Because,
     * it tries to access the properties of child object,
     * which is lazily loaded. Ideally, the child object should have
     * loaded through its service before accessing its properties.
     */
    @GetMapping("/parents/child/name/throwsEx")
    public void generateLazyInitException() {
        Parent parent = parentService.create("some", "thing");
        Parent fetchedParent = parentService.retrieve(parent.getId());
        fetchedParent.getChild().getName(); // should throw lazy-init-exception
    }
    
    @GetMapping("/parents/child/name/correctApproach")
    public String getChildNameProperly() {
        Parent parent = parentService.create("some", "thing");
        Parent fetchedParent = parentService.retrieve(parent.getId());
        
        // loading the child object before accessing its properties
        Child child = childService.retrieve(fetchedParent.getChild().getId());
        
        return child.getName(); 
    }


}
