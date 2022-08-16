package com.joelvitorniino.resources;

import com.joelvitorniino.dto.AntDTO;
import com.joelvitorniino.dto.LadyBugDTO;
import com.joelvitorniino.models.Ant;
import com.joelvitorniino.models.LadyBug;
import com.joelvitorniino.services.LadyBugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/animals/ladybug")
public class LadyBugResource {
    @Autowired
    LadyBugService service;
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<LadyBugDTO>> findAll() {
        List<LadyBug> list = service.findAll();
        List<LadyBugDTO> listDto = list.stream().map(x -> new LadyBugDTO(x)).collect(Collectors.toList());

        return ResponseEntity.ok().body(listDto);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody LadyBugDTO objDto) {
        LadyBug obj = service.fromDTO(objDto);
        obj = service.insert(obj);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping(value = {"/{id}"})
    public ResponseEntity<LadyBugDTO> findById(@PathVariable Integer id) {
        LadyBug obj = service.findById(id).get();

        return ResponseEntity.ok().body(new LadyBugDTO(obj));
    }

    @RequestMapping(value = "/random", method = RequestMethod.GET)
    public ResponseEntity<String> randomLadyBug() {
        return ResponseEntity.ok().body(service.randomPhotoLadyBug());
    }
}
