package com.example.lazyinit.repository;

import com.example.lazyinit.entity.Child;
import com.example.lazyinit.entity.Parent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ChildRepository extends JpaRepository<Child, UUID> {
}
