package com.joelvitorniino.resources;

import com.joelvitorniino.dto.AntDTO;
import com.joelvitorniino.models.Ant;
import com.joelvitorniino.services.AntService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/animals/ant")
public class AntResource {
    @Autowired
    AntService service;
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<AntDTO>> findAll() {
        List<Ant> list = service.findAll();
        List<AntDTO> listDto = list.stream().map(x -> new AntDTO(x)).collect(Collectors.toList());

        return ResponseEntity.ok().body(listDto);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody AntDTO objDto) {
        Ant obj = service.fromDTO(objDto);
        obj = service.insert(obj);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/random", method = RequestMethod.GET)
    public ResponseEntity<String> randomAnt() {
        return ResponseEntity.ok().body(service.randomPhotoAnt());
    }
}
