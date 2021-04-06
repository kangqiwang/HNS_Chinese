package com.kang.medicalwebapplication.config;

import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.experimental.var;
import reactor.netty.tcp.TcpClient;
import reactor.netty.http.client.HttpClient;

@Configuration
@EnableWebFlux
public class WebClientConfiguration {

	private static final String BASE_URL_STRING="https://api.nhs.uk";
	public static final int TIMEOUT = 1000;
	
	@Bean
	public WebClient webClientWithTimClient() {
		final var tcpClient = TcpClient.create().option(ChannelOption.CONNECT_TIMEOUT_MILLIS, TIMEOUT).doOnConnect(connection ->{
			new ReadTimeoutHandler(TIMEOUT,TimeUnit.MILLISECONDS);
			new WriteTimeoutHandler(TIMEOUT,TimeUnit.MILLISECONDS);
		});
		
		return WebClient.builder().baseUrl(BASE_URL_STRING).clientConnector(new ReactorClientHttpConnector(HttpClient.from(tcpClient)))
				.build();
	}
}
