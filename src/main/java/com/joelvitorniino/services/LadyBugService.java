package com.joelvitorniino.services;

import com.joelvitorniino.dto.LadyBugDTO;
import com.joelvitorniino.models.Ant;
import com.joelvitorniino.models.LadyBug;
import com.joelvitorniino.repository.LadyBugRepository;
import com.joelvitorniino.services.exception.ImageNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class LadyBugService {
    @Autowired
    private LadyBugRepository repository;

    public List<LadyBug> findAll() {
        return repository.findAll();
    }

    public String randomPhotoLadyBug() {
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

    public LadyBug insert(LadyBug obj) {
        return repository.save(obj);
    }

    public Optional<LadyBug> findById(Integer id) {
        Optional<LadyBug> ladybug = repository.findById(id);

        if(ladybug.isPresent()) {
            return ladybug;
        } else {
            ladybug.orElseThrow(() -> new ImageNotFoundException("Image is not found"));
        }

        return ladybug;
    }


    public LadyBug fromDTO(LadyBugDTO objDto) {
        return new LadyBug(objDto.getId(), objDto.getUrl());
    }
}
