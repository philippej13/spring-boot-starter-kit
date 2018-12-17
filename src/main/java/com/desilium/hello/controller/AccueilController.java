package com.desilium.hello.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accueil")
public class AccueilController {
    @RequestMapping(method = RequestMethod.GET, value = "/echo")
    public ResponseEntity<String> getAccount() {
        return ResponseEntity.ok("echo");
    }
}
