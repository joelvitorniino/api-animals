package com.joelvitorniino.resources;

import com.joelvitorniino.models.Ant;
import com.joelvitorniino.services.AntService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/animals/ant")
public class AntResource {
    @Autowired
    AntService service;
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Ant>> getAnt() {

        return ResponseEntity.ok().body(service.findAll());
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody Ant objParameter) {
        Ant obj = service.insert(objParameter);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/random", method = RequestMethod.GET)
    public ResponseEntity<String> randomAnt() {
        return ResponseEntity.ok().body(service.randomPhotoAnt());
    }
}
