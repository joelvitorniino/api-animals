package com.joelvitorniino.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/animals/monkey")
public class MonkeyResource {
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<String> getMonkey() {
        return ResponseEntity.ok().body("Hello, World!");
    }
}
