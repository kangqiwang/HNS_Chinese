package com.kang.medicalwebapplication.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller("/highlanderuk")

public class HighlanderController {
    
    @PostMapping("/createFolder")
    public ResponseEntity<String> createFolder(@RequestBody Map<String, Object> payload) throws Exception {
        
        return null;
    }


}
