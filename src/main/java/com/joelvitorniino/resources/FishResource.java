package com.joelvitorniino.resources;

import com.joelvitorniino.models.Fish;
import com.joelvitorniino.services.FishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/animals/fish")
public class FishResource {
    @Autowired
    FishService service;
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Fish>> getFish() {

        return ResponseEntity.ok().body(service.findAll());
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody Fish objParameter) {
        Fish obj = service.insert(objParameter);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/random", method = RequestMethod.GET)
    public ResponseEntity<String> randomFish() {
        return ResponseEntity.ok().body(service.randomPhotoFish());
    }
}
