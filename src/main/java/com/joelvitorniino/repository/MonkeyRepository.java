package com.joelvitorniino.repository;

import com.joelvitorniino.models.Monkey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonkeyRepository extends JpaRepository<Monkey, Integer> {
}
