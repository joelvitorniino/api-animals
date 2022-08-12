package com.joelvitorniino.services;

import com.joelvitorniino.dto.BirdDTO;
import com.joelvitorniino.models.Bird;
import com.joelvitorniino.repository.BirdRepository;
import com.joelvitorniino.services.exception.ImageNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    public void deleteById(Integer id) {
        findById(id);
        repository.deleteById(id);
    }

    public Optional<Bird> findById(Integer id) {
        Optional<Bird> bird = repository.findById(id);

        if(bird.isPresent()) {
            return bird;
        } else {
            bird.orElseThrow(() -> new ImageNotFoundException("Image is not found"));
        }

        return bird;
    }

    public Bird update(Bird obj) {
        Bird newObj = repository.findById(obj.getId()).get();
        updateData(newObj, obj);

        return repository.save(newObj);
    }

    private void updateData(Bird newObj, Bird obj) {
        newObj.setURL(obj.getURL());
    }

    public Bird fromDTO(BirdDTO objDto) {
        return new Bird(objDto.getId(), objDto.getUrl());
    }
}
