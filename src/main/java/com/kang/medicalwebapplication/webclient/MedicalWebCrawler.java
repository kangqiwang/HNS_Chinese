package com.kang.medicalwebapplication.webclient;

import org.springframework.http.MediaType;

import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

public class MedicalWebCrawler {
	private WebClient webClient = WebClient.create("https://api.nhs.uk");
	
//	private Mono<ClientResponse> responseMono = webClient.get().uri("").accept(MediaType.APPLICATION_JSON).header(headerName, headerValues).exchange();
	
//	public String getResult() {
//        return ">> Result Mono<ClientResponse> = " + responseMono.flatMap(res -> res.bodyToMono(String.class)).block();
//	}
	
	
}
