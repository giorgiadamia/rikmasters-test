package com.example.carservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class MoneyService {

    private final WebClient webClient;

    public void redWithdraw(Long driverId, Double amount) {
        Map<String, Double> requestBody = new HashMap<>();
        requestBody.put("red_dollar_balance", amount);

        webClient.put()
                .uri("http://localhost:8080/api/v1/account/" + driverId + "/red/sub")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(requestBody))
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,
                        clientResponse -> Mono.error(new IllegalArgumentException("Red dollar withdraw error")))
                .bodyToMono(String.class)
                .block();
    }

    public void redDeposit(Long driverId, Double amount) {
        Map<String, Double> requestBody = new HashMap<>();
        requestBody.put("red_dollar_balance", amount);

        webClient.put()
                .uri("http://localhost:8080/api/v1/account/" + driverId + "/red/add")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(requestBody))
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,
                        clientResponse -> Mono.error(new IllegalArgumentException("Red dollar deposit error")))
                .bodyToMono(String.class)
                .block();
    }

    public void blueWithdraw(Long driverId, Double amount) {
        Map<String, Double> requestBody = new HashMap<>();
        requestBody.put("blue_dollar_balance", amount);

        webClient.put()
                .uri("http://localhost:8080/api/v1/account/" + driverId + "/blue/sub")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(requestBody))
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,
                        clientResponse -> Mono.error(new IllegalArgumentException("Blue dollar withdraw error")))
                .bodyToMono(String.class)
                .block();
    }

    public void blueDeposit(Long driverId, Double amount) {
        Map<String, Double> requestBody = new HashMap<>();
        requestBody.put("blue_dollar_balance", amount);

        webClient.put()
                .uri("http://localhost:8080/api/v1/account/" + driverId + "/blue/add")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(requestBody))
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,
                        clientResponse -> Mono.error(new IllegalArgumentException("Blue dollar deposit error")))
                .bodyToMono(String.class)
                .block();
    }

    public void greenWithdraw(Long driverId, Double amount) {
        Map<String, Double> requestBody = new HashMap<>();
        requestBody.put("green_dollar_balance", amount);

        webClient.put()
                .uri("http://localhost:8080/api/v1/account/" + driverId + "/green/sub")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(requestBody))
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,
                        clientResponse -> Mono.error(new IllegalArgumentException("Green dollar withdraw error")))
                .bodyToMono(String.class)
                .block();
    }

    public void greenDeposit(Long driverId, Double amount) {
        Map<String, Double> requestBody = new HashMap<>();
        requestBody.put("green_dollar_balance", amount);

        webClient.put()
                .uri("http://localhost:8080/api/v1/account/" + driverId + "/green/add")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(requestBody))
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,
                        clientResponse -> Mono.error(new IllegalArgumentException("Green dollar deposit error")))
                .bodyToMono(String.class)
                .block();
    }
}
