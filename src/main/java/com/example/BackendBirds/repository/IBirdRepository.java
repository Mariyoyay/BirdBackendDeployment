package com.example.BackendBirds.repository;

import com.example.BackendBirds.model.Bird;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBirdRepository extends JpaRepository<Bird, Integer> { }
