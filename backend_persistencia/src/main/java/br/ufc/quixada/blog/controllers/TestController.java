package br.ufc.quixada.blog.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/alive")
public class TestController {
    
    @GetMapping()
    public String isAlive() {
        return "Alive";
    }
    

}
