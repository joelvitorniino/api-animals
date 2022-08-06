package com.joelvitorniino.resources;

import com.joelvitorniino.dto.MacawDTO;
import com.joelvitorniino.models.Macaw;
import com.joelvitorniino.services.MacawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/animals/macaw")
public class MacawResource {
    @Autowired
    MacawService service;
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<MacawDTO>> findAll() {
        List<Macaw> list = service.findAll();
        List<MacawDTO> listDto = list.stream().map(x -> new MacawDTO(x)).collect(Collectors.toList());

        return ResponseEntity.ok().body(listDto);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody MacawDTO objDto) {
        Macaw obj = service.fromDTO(objDto);
        obj = service.insert(obj);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/random", method = RequestMethod.GET)
    public ResponseEntity<String> randomMacaw() {
        return ResponseEntity.ok().body(service.randomPhotoMacaw());
    }
}
