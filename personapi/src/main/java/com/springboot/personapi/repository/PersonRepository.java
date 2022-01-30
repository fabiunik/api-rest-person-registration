package com.springboot.personapi.repository;

import com.springboot.personapi.entity.Person;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
    
}
