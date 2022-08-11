package com.joelvitorniino.repository;

import com.joelvitorniino.models.Bird;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BirdRepository extends JpaRepository<Bird, Integer> {
}