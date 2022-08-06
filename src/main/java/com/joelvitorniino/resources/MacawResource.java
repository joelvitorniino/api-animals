package com.joelvitorniino.resources;

import com.joelvitorniino.models.Macaw;
import com.joelvitorniino.services.MacawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/animals/macaw")
public class MacawResource {
    @Autowired
    MacawService service;
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Macaw>> getMacaw() {

        return ResponseEntity.ok().body(service.findAll());
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody Macaw objParameter) {
        Macaw obj = service.insert(objParameter);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/random", method = RequestMethod.GET)
    public ResponseEntity<String> randomMacaw() {
        return ResponseEntity.ok().body(service.randomPhotoMacaw());
    }
}
