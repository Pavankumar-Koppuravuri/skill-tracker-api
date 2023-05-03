package com.skilltracker.skilltrackerapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skilltracker.skilltrackerapi.entity.Person;



@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
