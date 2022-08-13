package com.joelvitorniino.services;

import com.joelvitorniino.dto.AntDTO;
import com.joelvitorniino.models.Ant;
import com.joelvitorniino.models.Bird;
import com.joelvitorniino.repository.AntRepository;
import com.joelvitorniino.services.exception.ImageNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    public Optional<Ant> findById(Integer id) {
        Optional<Ant> ant = repository.findById(id);

        if(ant.isPresent()) {
            return ant;
        } else {
            ant.orElseThrow(() -> new ImageNotFoundException("Image is not found"));
        }

        return ant;
    }

    public Ant fromDTO(AntDTO objDto) {
        return new Ant(objDto.getId(), objDto.getUrl());
    }
}
