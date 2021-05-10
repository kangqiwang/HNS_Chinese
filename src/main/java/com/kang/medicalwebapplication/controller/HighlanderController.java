package com.kang.medicalwebapplication.controller;

import java.io.ObjectInputFilter.Status;
import java.util.Map;

import com.kang.medicalwebapplication.service.HighlanderService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController("/highlanderuk/")

public class HighlanderController {
    
    @PostMapping("/createFolder")
    public ResponseEntity<String> createFolder(@RequestBody Map<String, Object> payload) throws Exception {
        HighlanderService highlanderService = new HighlanderService();
        log.debug("highlanderService",highlanderService);
        log.error("this is highlanderService createFolder");
        return new ResponseEntity<String>("this is a string",HttpStatus.OK);
    }


    @GetMapping("/createFolder")
    public ResponseEntity<String> getFolder() throws Exception {
        HighlanderService highlanderService = new HighlanderService();
        
        log.error("this is highlanderService createFolder");
        return null;
    }


}
