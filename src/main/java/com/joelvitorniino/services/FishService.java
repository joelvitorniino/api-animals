package com.joelvitorniino.services;

import com.joelvitorniino.dto.FishDTO;
import com.joelvitorniino.models.Fish;
import com.joelvitorniino.repository.FishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class FishService {
    @Autowired
    private FishRepository repository;

    public List<Fish> findAll() {
        return repository.findAll();
    }

    public String randomPhotoFish() {
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

    public Fish insert(Fish obj) {
        return repository.save(obj);
    }

    public Fish fromDTO(FishDTO objDto) {
        return new Fish(objDto.getId(), objDto.getUrl());
    }
}
