package com.joelvitorniino.services;

import com.joelvitorniino.models.Ant;
import com.joelvitorniino.repository.AntRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class AntService {
    @Autowired
    private AntRepository repository;

    public List<Ant> findAll() {
        return repository.findAll();
    }

    public String randomPhotoAnt() {
        Random r = new Random();
        String randomElement = null;

        int numberOfElements = 2;

        for (int i = 0; i < numberOfElements; i++) {
            int randomIndex = r.nextInt(findAll().size());
            randomElement = String.valueOf(findAll().get(randomIndex));
            findAll().remove(randomIndex);
        }

        return randomElement;
    }

    public Ant insert(Ant obj) {
        return repository.save(obj);
    }
}
