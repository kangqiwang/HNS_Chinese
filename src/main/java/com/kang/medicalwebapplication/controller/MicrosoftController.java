package com.kang.medicalwebapplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class MicrosoftController {

	@GetMapping("/")
	public String microsoftController() {
		return "";
		
	}
}
