package com.joelvitorniino.services;

import com.joelvitorniino.dto.BirdDTO;
import com.joelvitorniino.models.Bird;
import com.joelvitorniino.repository.BirdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class BirdService {
    @Autowired
    private BirdRepository repository;

    public List<Bird> findAll() {
        return repository.findAll();
    }

    public String randomPhotoBird() {
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

    public Bird insert(Bird obj) {
        return repository.save(obj);
    }

    public Bird fromDTO(BirdDTO objDto) {
        return new Bird(objDto.getId(), objDto.getUrl());
    }
}
