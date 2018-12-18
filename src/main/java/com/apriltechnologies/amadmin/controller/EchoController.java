package com.apriltechnologies.amadmin.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class EchoController {
    @RequestMapping(method = RequestMethod.GET, value = "/echo")
    public ResponseEntity<String> getAccount() {
        return ResponseEntity.ok("echo");
    }
}
