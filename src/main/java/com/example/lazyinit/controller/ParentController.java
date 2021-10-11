package com.example.lazyinit.controller;

import com.example.lazyinit.entity.Parent;
import com.example.lazyinit.model.ParentModel;
import com.example.lazyinit.service.ParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
public class ParentController {

    final ParentService parentService;

    public ParentController(ParentService parentService) {
        this.parentService = parentService;
    }

    @GetMapping("/ping")
    public String ping() {
        System.out.println("Servicing the request....");
        return String.format("Server is UP");
    }

    @PostMapping("/parents")
    public ResponseEntity<ParentModel> create(@RequestBody ParentModel parentModel) {
        Parent parent = parentService.create(parentModel.getName(), parentModel.getChildName());
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(parent.getId())
                .toUri();
        return ResponseEntity.created(uri)
                .body(toParentModel(parent));
    }

    @GetMapping("/parents/{id}")
    public ParentModel retrieve(@PathVariable UUID id) {
        Parent parent = parentService.retrieve(id);
        return toParentModel(parent);
    }

    private ParentModel toParentModel(Parent parent) {
        final ParentModel parentModel = new ParentModel();
        parentModel.setName(parent.getName());
        parentModel.setChildName(parent.getChild().getName()); // should throw lazy-init-exception
        return parentModel;
    }

}
