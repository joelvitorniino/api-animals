package com.joelvitorniino.repository;

import com.joelvitorniino.models.Fish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FishRepository extends JpaRepository<Fish, Integer> {
}
