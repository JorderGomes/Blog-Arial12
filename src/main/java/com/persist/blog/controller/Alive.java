package com.persist.blog.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class Alive {
    @GetMapping("/isup")
    public ResponseEntity<String> isUp(){
        return ResponseEntity.ok("Api is up!");
    }
}
