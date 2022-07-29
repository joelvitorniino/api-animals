package com.joelvitorniino.resources;

import com.joelvitorniino.models.Monkey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.joelvitorniino.services.MonkeyService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/animals/monkey")
public class MonkeyResource {
    @Autowired
    MonkeyService service;
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Monkey>> getMonkey() {

        return ResponseEntity.ok().body(service.findAll());
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody Monkey objParameter) {
        Monkey obj = service.insert(objParameter);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/random", method = RequestMethod.GET)
    public ResponseEntity<String> randomMonkey() {
        return ResponseEntity.ok().body(service.randomPhotoMonkey());
    }
}
