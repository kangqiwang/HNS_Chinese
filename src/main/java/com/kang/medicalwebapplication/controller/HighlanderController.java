package com.kang.medicalwebapplication.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController

public class HighlanderController {
    @PostMapping("/createFolder")
    public ResponseEntity<String> createFolder(@RequestBody Map<String, Object> payload) throws Exception {
        log.debug("this is createFolder", "createFolder");
        return new ResponseEntity<String>("Hello World!", HttpStatus.OK);
    }
     
    @PostMapping("/createFolder")
    public ResponseEntity<String> getFolder(@RequestBody Map<String, Object> payload) throws Exception {
        log.debug("this is createFolder", "createFolder");
        return new ResponseEntity<String>("Hello World!", HttpStatus.OK);
    }


}
