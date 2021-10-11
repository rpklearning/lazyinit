package com.example.lazyinit.entity;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@MappedSuperclass
abstract public class Base extends AbstractPersistable<UUID> {
}
