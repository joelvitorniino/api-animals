package com.joelvitorniino.resources;

import com.joelvitorniino.models.LadyBug;
import com.joelvitorniino.services.LadyBugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/animals/ladybug")
public class LadyBugResource {
    @Autowired
    LadyBugService service;
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<LadyBug>> getLadyBug() {

        return ResponseEntity.ok().body(service.findAll());
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody LadyBug objParameter) {
        LadyBug obj = service.insert(objParameter);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/random", method = RequestMethod.GET)
    public ResponseEntity<String> randomLadyBug() {
        return ResponseEntity.ok().body(service.randomPhotoLadyBug());
    }
}
