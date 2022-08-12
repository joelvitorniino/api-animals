package com.joelvitorniino.resources;

import com.joelvitorniino.dto.BirdDTO;
import com.joelvitorniino.models.Bird;
import com.joelvitorniino.services.BirdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/animals/bird")
public class BirdResource {
    @Autowired
    BirdService service;
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<BirdDTO>> findAll() {
        List<Bird> list = service.findAll();
        List<BirdDTO> listDto = list.stream().map(x -> new BirdDTO(x)).collect(Collectors.toList());

        return ResponseEntity.ok().body(listDto);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody BirdDTO objDto) {
        Bird obj = service.fromDTO(objDto);
        obj = service.insert(obj);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/random", method = RequestMethod.GET)
    public ResponseEntity<String> randomBird() {
        return ResponseEntity.ok().body(service.randomPhotoBird());
    }
}
