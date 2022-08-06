package com.joelvitorniino.resources;

import com.joelvitorniino.dto.FishDTO;
import com.joelvitorniino.models.Fish;
import com.joelvitorniino.services.FishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/animals/fish")
public class FishResource {
    @Autowired
    FishService service;
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<FishDTO>> findAll() {
        List<Fish> list = service.findAll();
        List<FishDTO> listDto = list.stream().map(x -> new FishDTO(x)).collect(Collectors.toList());

        return ResponseEntity.ok().body(listDto);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody FishDTO objDto) {
        Fish obj = service.fromDTO(objDto);
        obj = service.insert(obj);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/random", method = RequestMethod.GET)
    public ResponseEntity<String> randomFish() {
        return ResponseEntity.ok().body(service.randomPhotoFish());
    }
}
