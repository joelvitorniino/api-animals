package com.joelvitorniino.services;

import com.joelvitorniino.dto.MacawDTO;
import com.joelvitorniino.models.Macaw;
import com.joelvitorniino.repository.MacawRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class MacawService {
    @Autowired
    private MacawRepository repository;

    public List<Macaw> findAll() {
        return repository.findAll();
    }

    public String randomPhotoMacaw() {
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

    public Macaw insert(Macaw obj) {
        return repository.save(obj);
    }

    public Macaw fromDTO(MacawDTO objDto) {
        return new Macaw(objDto.getId(), objDto.getUrl());
    }
}
