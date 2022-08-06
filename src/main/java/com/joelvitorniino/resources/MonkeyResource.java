package com.joelvitorniino.resources;

import com.joelvitorniino.dto.MonkeyDTO;
import com.joelvitorniino.models.Monkey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.joelvitorniino.services.MonkeyService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/animals/monkey")
public class MonkeyResource {
    @Autowired
    MonkeyService service;
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<MonkeyDTO>> findAll() {
        List<Monkey> list = service.findAll();
        List<MonkeyDTO> listDto = list.stream().map(x -> new MonkeyDTO(x)).collect(Collectors.toList());

        return ResponseEntity.ok().body(listDto);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody MonkeyDTO objDto) {
        Monkey obj = service.fromDTO(objDto);
        obj = service.insert(obj);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/random", method = RequestMethod.GET)
    public ResponseEntity<String> randomMonkey() {
        return ResponseEntity.ok().body(service.randomPhotoMonkey());
    }
}
