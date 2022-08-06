package com.joelvitorniino.services;

import com.joelvitorniino.dto.MonkeyDTO;
import com.joelvitorniino.models.Monkey;
import com.joelvitorniino.repository.MonkeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class MonkeyService {
    @Autowired
    private MonkeyRepository repository;

    public List<Monkey> findAll() {
        return repository.findAll();
    }

    public String randomPhotoMonkey() {
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

    public Monkey insert(Monkey obj) {
        return repository.save(obj);
    }

    public Monkey fromDTO(MonkeyDTO objDto) {
        return new Monkey(objDto.getId(), objDto.getUrl());
    }
}
